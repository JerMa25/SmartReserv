import {Typography, Paper, Button, Box, Toolbar, Stack, Chip } from '@mui/material';
import AccountBalanceTwoToneIcon from '@mui/icons-material/AccountBalanceTwoTone';
import React, { useState, useEffect } from 'react'
import toast from 'react-hot-toast';
import axios from 'axios';
import EventIcon from '@mui/icons-material/Event';
import ScheduleIcon from '@mui/icons-material/Schedule';
import DeleteIcon from '@mui/icons-material/Delete';

export default function MesReservations() {
  const utilisateur = JSON.parse(localStorage.getItem("utilisateur"));
  const [reservationsSalle, setReservationsSalle] = useState([]);
  const [reservationsMat, setReservationsMat] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/reservationSalle/enseignant/${utilisateur.matricule}`)
      .then((res) => {
        setReservationsSalle(res.data);
      })
      .catch((err) => {
        toast.error("Erreur lors de la récupération de vos réservations :", err);
      });
  }, []);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/reservationMat/enseignant/${utilisateur.matricule}`)
      .then((res) => {
        setReservationsMat(res.data);
      })
      .catch((err) => {
        toast.error("Erreur lors de la récupération de vos réservations :", err);
      });
  }, []);

  return (
    <Box sx={{ padding: 3 }}>
  <Toolbar sx={{ 
    backgroundColor: "#1d9ee9", 
    color: "#fff", 
    mb: 3, 
    position: "sticky", 
    top: 0, 
    zIndex: 1,
    borderRadius: 1
  }}>
    <Typography variant="h5" fontWeight="bold">RÉSERVATIONS DE SALLES</Typography>
  </Toolbar> 
  
  <Stack spacing={3}>
    {reservationsSalle.map((reservsalle) => (
      <Paper
        key={reservsalle.id}
        elevation={3}
        sx={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          p: 3,
          mb: 3,
          borderRadius: 3,
          backgroundColor: "#f8fafc",
          boxShadow: "0px 4px 12px rgba(0,0,0,0.15)",
          minWidth: 600  // Largeur minimale augmentée
        }}
      >
        {/* Icon and Info Section */}
        <Box display="flex" alignItems="center" gap={4}>
          <Box sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            backgroundColor: '#e0f2fe',
            borderRadius: '50%',
            p: 2,
            minWidth: 64,
            minHeight: 64
          }}>
            <AccountBalanceTwoToneIcon sx={{ 
              fontSize: 40,  // Taille d'icône augmentée
              color: '#1d4ed8' 
            }} />
          </Box>

          {/* Room Information */}
          <Box sx={{ minWidth: 400 }}>
            <Box display="flex" gap={3} alignItems="center" mb={2}>
              <Typography variant="h5" fontWeight="bold" color="primary">
                Salle {reservsalle.salleId.id}
              </Typography>
              <Chip 
                label={`${reservsalle.salleId.nbPlaces} places`} 
                size="medium"
                sx={{ 
                  backgroundColor: '#e0f2fe', 
                  color: '#1d4ed8',
                  fontWeight: 'bold',
                  fontSize: '1rem',
                  p: 1.5
                }} 
              />
            </Box>
            
            <Typography variant="h6" mb={2}>
              {reservsalle.salleId.description || "Aucune description"}
            </Typography>
            
            <Box display="flex" gap={4}>
              <Box display="flex" alignItems="center" gap={1}>
                <EventIcon fontSize="large" color="action" />
                <Typography variant="h6">
                  {new Date(reservsalle.date).toLocaleDateString('fr-FR', {
                    weekday: 'short',
                    day: 'numeric',
                    month: 'short',
                    year: 'numeric'
                  })}
                </Typography>
              </Box>
              
              <Box display="flex" alignItems="center" gap={1}>
                <ScheduleIcon fontSize="large" color="action" />
                <Typography variant="h6">
                  {reservsalle.horaire}
                </Typography>
              </Box>
            </Box>
          </Box>
        </Box>

        {/* Delete Button */}
        <Button 
          variant="contained" 
          startIcon={<DeleteIcon sx={{ fontSize: 28 }} />}
          size="large"
          sx={{
            backgroundColor: "#ef4444",
            color: "white",
            px: 3,
            py: 1.5,
            borderRadius: 2,
            fontSize: '1.1rem',
            fontWeight: 'bold',
            '&:hover': {
              backgroundColor: "#dc2626"
            }
          }}
          onClick={() => {
            axios.put(`http://localhost:8080/api/reservationSalle/cancel/${reservsalle.id}`)
            .then(() => {
              toast.success("Réservation de salle supprimée !");
              setReservationsSalle((prev) => prev.filter(r => r.id !== reservsalle.id));
            })
            .catch(() => {
              toast.error("Erreur lors de la suppression de la réservation.");
            });
          }}
        >
          Supprimer
        </Button>
      </Paper>
    ))}
  </Stack>



      <Toolbar sx={{ mt: 2, backgroundColor: "#1d9ee9ff", color: "#fff", mb: 2, position: "sticky", top: 0, zIndex: 1 }}>
        <Typography variant="h6"><strong>MATÉRIEL PÉDAGOGIQUE</strong></Typography>
      </Toolbar> 

      <Stack spacing={3}>
    {reservationsMat.map((reservmat) => (
      <Paper
        key={reservmat.id}
        elevation={3}
        sx={{
          display: 'flex',
          justifyContent: 'space-between',
          alignItems: 'center',
          p: 3,
          mb: 3,
          borderRadius: 3,
          backgroundColor: "#f8fafc",
          boxShadow: "0px 4px 12px rgba(0,0,0,0.15)",
          minWidth: 600  // Largeur minimale augmentée
        }}
      >
        {/* Icon and Info Section */}
        <Box display="flex" alignItems="center" gap={4}>
          <Box sx={{
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            backgroundColor: '#e0f2fe',
            borderRadius: '50%',
            p: 2,
            minWidth: 64,
            minHeight: 64
          }}>
            <AccountBalanceTwoToneIcon sx={{ 
              fontSize: 40,  // Taille d'icône augmentée
              color: '#1d4ed8' 
            }} />
          </Box>

          {/* Room Information */}
          <Box sx={{ minWidth: 400 }}>
            <Box display="flex" gap={3} alignItems="center" mb={2}>
              <Typography variant="h5" fontWeight="bold" color="primary">
                {reservmat.matId.nom}
              </Typography>
              <Chip 
                label={`${reservmat.matId.marque} - ${reservmat.matId.modele}`} 
                size="medium"
                sx={{ 
                  backgroundColor: '#e0f2fe', 
                  color: '#1d4ed8',
                  fontWeight: 'bold',
                  fontSize: '1rem',
                  p: 1.5
                }} 
              />
            </Box>
            
            <Typography variant="h6" mb={2}>
              {reservmat.matId.stockage && reservmat.matId.memoire
              ? `Stockage: ${reservmat.matId.stockage} - Mémoire: ${reservmat.matId.memoire}`
              : `Fréquence: ${reservmat.matId.frequence} - Résolution: ${reservmat.matId.resolution}`}
            </Typography>
            
            <Box display="flex" gap={4}>
              <Box display="flex" alignItems="center" gap={1}>
                <EventIcon fontSize="large" color="action" />
                <Typography variant="h6">
                  {new Date(reservmat.date).toLocaleDateString('fr-FR', {
                    weekday: 'short',
                    day: 'numeric',
                    month: 'short',
                    year: 'numeric'
                  })}
                </Typography>
              </Box>
              
              <Box display="flex" alignItems="center" gap={1}>
                <ScheduleIcon fontSize="large" color="action" />
                <Typography variant="h6">
                  {reservmat.horaire}
                </Typography>
              </Box>
            </Box>
          </Box>
        </Box>

        {/* Delete Button */}
        <Button 
          variant="contained" 
          startIcon={<DeleteIcon sx={{ fontSize: 28 }} />}
          size="large"
          sx={{
            backgroundColor: "#ef4444",
            color: "white",
            px: 3,
            py: 1.5,
            borderRadius: 2,
            fontSize: '1.1rem',
            fontWeight: 'bold',
            '&:hover': {
              backgroundColor: "#dc2626"
            }
          }}
          onClick={() => {
            axios.put(`http://localhost:8080/api/reservationMat/cancel/${reservmat.id}`)
            .then(() => {
              toast.success("Réservation de matériel supprimée !");
              setReservationsMat((prev) => prev.filter(r => r.id !== reservmat.id));
            })
            .catch(() => {
              toast.error("Erreur lors de la suppression de la réservation.");
            });
          }}
        >
          Supprimer
        </Button>
      </Paper>
    ))}
  </Stack>     
    </Box>
  )
}
