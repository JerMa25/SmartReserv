import { Box, AppBar, Toolbar, Stack, Typography, TextField, Button, Checkbox, FormControlLabel } from '@mui/material'
import { useForm } from "react-hook-form" 
import React, { useEffect } from 'react'
import toast from 'react-hot-toast'
import axios from 'axios'
import { Link, useNavigate } from 'react-router-dom'
import Header from './Header';

export default function Connexion() {
  const [formations, setFormations] = React.useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    if(localStorage.getItem("utilisateur")){
      navigate("/");
    }
  }, []);

  useEffect(() => {
    axios.get('http://localhost:8080/api/formation')
      .then((res) => {
        setFormations(res.data); // Stockage dans une variable d'état
      })
      .catch((err) => {
        console.error("Erreur lors de la récupération des formations :", err);
      });
  }, []);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data) => {
    try {
      const response = await axios.post("http://localhost:8080/api/enseignants/login", {
        matricule: data.matricule,
        password: data.password
      });

      const enseignant = response.data;

      // Vérifie si ce matricule est celui d'un responsable de formation
      const isResponsable = formations.some(f =>  
        f.responsableId?.matricule === enseignant.matricule
      );
      
      // Tu peux stocker cette info dans l'objet utilisateur
      const utilisateur = { ...enseignant, isResponsable };

      localStorage.setItem("utilisateur", JSON.stringify(utilisateur));
      toast.success("Connexion réussie");
      navigate("/");
    } catch (error) {
      toast.error("Matricule ou mot de passe incorrect");
    }
  };

  return (
    <>
        <Header/>
        <Stack alignItems={"center"}
        justifyContent={"center"}
        width={"100%"}
        height={"100vh"}
        bgcolor={"#3b82f6"}>
        <Box width={400}
            sx={{
            bgcolor: "white",
            padding: 4,
            borderRadius: 2,
            boxShadow: 3
            }}> 
            <Typography variant='h4' align='center' gutterBottom sx={{ color: "#3b82f6", fontWeight: 'bold' }}>
            Connexion SmartReserv
            </Typography>
            <Typography variant='subtitle1' align='center' gutterBottom sx={{ mb: 3, color: "#3b82f6" }}>
            Accédez à vos réservations de salles et équipements
            </Typography>

            <form onSubmit={handleSubmit(onSubmit)}>
            <Stack direction={"column"} gap={2}>
                <Typography variant='h6' sx={{fontWeight:"bold"}}>Matricule</Typography>
                <TextField
                variant="outlined"
                fullWidth
                size='small'
                placeholder="Entrez votre matricule"
                {...register("matricule", { required: "Veuillez saisir votre matricule" })}
                />

                <Typography variant='h6' sx={{fontWeight:"bold"}}>Mot de passe</Typography>
                <TextField
                variant="outlined"
                fullWidth
                size='small'
                type='password'
                placeholder="Entrez votre mot de passe"
                {...register("password", { 
                    required: "Veuillez saisir votre mot de passe",
                    minLength: {value: 4, message: "4 caractères minimum"}
                })}
                />

                <Stack direction="row" justifyContent="space-between" alignItems="center">
                <FormControlLabel 
                    control={<Checkbox size="small" />} 
                    label="Se souvenir de moi" 
                />
                <Link to="#" style={{ fontSize: '0.875rem', color: '#3b82f6' }}>Mot de passe oublié ?</Link>
                </Stack>

                <Button 
                variant="contained"
                fullWidth
                type='submit'
                sx={{
                    bgcolor: "#3b82f6",
                    '&:hover': { bgcolor: "#2563eb" },
                    mt: 2,
                    py: 1
                }}>
                Se connecter
                </Button>
            </Stack>
            </form>
        </Box>
        </Stack>
    </>
  )
}