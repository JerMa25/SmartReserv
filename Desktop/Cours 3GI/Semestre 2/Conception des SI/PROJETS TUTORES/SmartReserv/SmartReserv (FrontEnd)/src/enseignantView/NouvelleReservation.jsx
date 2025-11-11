import React, { useEffect, useState } from 'react';
import { Box, Typography, Paper, Button, CircularProgress } from '@mui/material';
import AccountBalanceTwoToneIcon from '@mui/icons-material/AccountBalanceTwoTone';
import ComputerIcon from '@mui/icons-material/Computer';
import AlbumTwoToneIcon from '@mui/icons-material/AlbumTwoTone';
import ReservForm from '../ReservForm';
import axios from 'axios';
import toast from 'react-hot-toast';

export default function NouvelleReservation() {
  const today = new Date().toISOString().split('T')[0];
  const utilisateur = JSON.parse(localStorage.getItem("utilisateur"));

  const [loadingSalle, setLoadingSalle] = useState(false);
  const [loadingOrdi, setLoadingOrdi] = useState(false);
  const [loadingVideoProj, setLoadingVideoProj] = useState(false);

  const [sallesDisponibles, setSallesDisponibles] = useState([]);
  const [ordinateursDisponibles, setOrdinateursDisponibles] = useState([]);
  const [videoProjsDisponibles, setVideoProjsDisponibles] = useState([]);

  const [reservSalleData, setReservSalleData] = useState({
    date: today,
    horaire: '',
    capacite: '',
  });
  const [reservOrdinateurData, setReservOrdinateurData] = useState({
    date: today,
    horaire: '',
    marque: '',
  });
  const [reserVideoProjData, setReserVideoProjData] = useState({
    date: today,
    horaire: '',
    marque: '',
  });
  const handleSearchSalles = async () => {
    if (!reservSalleData.horaire) {
      toast.error("Veuillez choisir un horaire.");
      return;
    }

    const today = new Date();
    const selectedDate = new Date(reservSalleData.date);
    const [startHour] = reservSalleData.horaire.split(' - ');
    const [startHourH, startHourM] = startHour.split(':').map(Number);
    const selectedStartTime = new Date(selectedDate);
    selectedStartTime.setHours(startHourH, startHourM, 0, 0);

    
    // même jour, mais horaire déjà passé
    if (
      selectedDate.toDateString() === today.toDateString() &&
      selectedStartTime < today
    ) {
      setSallesDisponibles([]);
      toast.error("Horaire déjà dépassé.");
      return;
    }

    setLoadingSalle(true);
    try {
      let response;

      if (reservSalleData.capacite) {
        // Appel de l'API avec la capacité
        response = await axios.get(
          `http://localhost:8080/api/classroom/disponible/${reservSalleData.capacite}`,{
          params: {
            date: reservSalleData.date,
            horaire: reservSalleData.horaire
          }
        });
      } else {
        // Appel de l'API sans la capacité
        response = await axios.get(
          'http://localhost:8080/api/classroom/disponible',{
          params: {
            date: reservSalleData.date,
            horaire: reservSalleData.horaire
          }
        });
      }

      if (response.data.length === 0) {
        toast("Aucune salle disponible.");
        setSallesDisponibles([]);
        return;
      }

      setSallesDisponibles(response.data);
      
    } catch (error) {
      toast.error("Erreur lors de la recherche de salles disponibles :", error);
    } finally {
      setLoadingSalle(false);
    }
  };

  const handleSearchOrdinateurs = async () => {
    if (!reservOrdinateurData.horaire) {
      toast.error("Veuillez choisir un horaire.");
      return;
    }
     
    const today = new Date();
    const selectedDate = new Date(reservOrdinateurData.date);
    const [startHour] = reservOrdinateurData.horaire.split(' - ');
    const [startHourH, startHourM] = startHour.split(':').map(Number);
    const selectedStartTime = new Date(selectedDate);
    selectedStartTime.setHours(startHourH, startHourM, 0, 0);

    
    // même jour, mais horaire déjà passé
    if (
      selectedDate.toDateString() === today.toDateString() &&
      selectedStartTime < today
    ) {
      setOrdinateursDisponibles([]);
      toast.error("Horaire déjà dépassé.");
      return;
    }
    
    try {
      let response;

      if (reservOrdinateurData.marque) {
        // Appel de l'API avec la marque
        response = await axios.get(
          `http://localhost:8080/api/ordinateur/disponible/${reservOrdinateurData.marque}`,{
          params: {
            date: reservOrdinateurData.date,
            horaire: reservOrdinateurData.horaire
          }
        });
      } else {
        // Appel de l'API sans la marque
        response = await axios.get(
          'http://localhost:8080/api/ordinateur/disponible',{
          params: {
            date: reservOrdinateurData.date,
            horaire: reservOrdinateurData.horaire
          }
        });
      } 

      if (response.data.length === 0) {
        toast("Aucun ordinateur disponible.");
        setOrdinateursDisponibles([]);
        return;
      }

      setOrdinateursDisponibles(response.data);
    } catch (error) {
      toast.error("Erreur lors de la recherche des ordinateurs disponibles :", error);
    }finally {
      setLoadingOrdi(false);
    }
  };

    const handleSearchVideoProjs = async () => {
    if (!reserVideoProjData.horaire) {
      toast.error("Veuillez choisir un horaire.");
      return;
    }

    const today = new Date();
    const selectedDate = new Date(reserVideoProjData.date);
    const [startHour] = reserVideoProjData.horaire.split(' - ');
    const [startHourH, startHourM] = startHour.split(':').map(Number);
    const selectedStartTime = new Date(selectedDate);
    selectedStartTime.setHours(startHourH, startHourM, 0, 0);

    
    // même jour, mais horaire déjà passé
    if (
      selectedDate.toDateString() === today.toDateString() &&
      selectedStartTime < today
    ) {
      setVideoProjsDisponibles([]);
      toast.error("Horaire déjà dépassé.");
      return;
    }

    try {
      let response;

      if (reserVideoProjData.marque) {
        // Appel de l'API avec la marque
        response = await axios.get(
          `http://localhost:8080/api/videoProj/disponible/${reserVideoProjData.marque}`,{
          params: {
            date: reserVideoProjData.date,
            horaire: reserVideoProjData.horaire
          }
        });
      } else {
        // Appel de l'API sans la marque
        response = await axios.get(
          'http://localhost:8080/api/videoProj/disponible',{
          params: {
            date: reserVideoProjData.date,
            horaire: reserVideoProjData.horaire
          }
        });
      }

      if (response.data.length === 0) {
        toast("Aucun vidéo projecteur disponible.");
        setVideoProjsDisponibles([]);
        return;
      }

      setVideoProjsDisponibles(response.data);
    } catch (error) {
      toast.error("Erreur lors de la recherche des vidéos projecteurs disponibles :", error);
    } finally {
      setLoadingVideoProj(false);
    }
  };

  const handleReservSalle = (salle) => {
    
    const payload = {
      date: reservSalleData.date, // ou autre champ venant du formulaire
      horaire: reservSalleData.horaire,
      salleId: salle,
      enseignantId: utilisateur
    };

    axios
      .post("http://localhost:8080/api/reservationSalle/newReservSalle", payload)
      .then(() => {
        toast.success("Réservation réussie !");
        // Mettre à jour la liste localement
        setSallesDisponibles((prev) => prev.filter((s) => s.id !== salle.id));
      })
      .catch((e) => {
        toast.error("Échec de la réservation !");
        console.log(e);
      });
  };

  const handleReservationMateriel = (materiel, type) => {
    // Choisir les données appropriées selon le type
    const reservationData = type === 'ordinateur' ? reservOrdinateurData : reserVideoProjData;
    
    const payload = {
      date: reservationData.date, 
      horaire: reservationData.horaire,
      matId: materiel.id,
      enseignantId: utilisateur,
      type
    };

    axios
      .post("http://localhost:8080/api/reservationMat/newReservMat", payload)
      .then(() => {
        toast.success("Réservation réussie !");
        // Mettre à jour la liste appropriée
        if (type === 'ordinateur') {
          setOrdinateursDisponibles(prev => prev.filter(s => s.id !== materiel.id));
        } else {
          setVideoProjsDisponibles(prev => prev.filter(s => s.id !== materiel.id));
        }
      })
      .catch((e) => {
        toast.error("Échec de la réservation !");
        console.log(e);
      });
  };

  return (
    <Box p={3}>
      <Typography variant="h4" fontWeight="bold" color='#3b82f6' textAlign="center" mb={3}>
        SALLE DE COURS
      </Typography>

      <ReservForm
        title="Reserve Data"
        formData={reservSalleData}
        setFormData={setReservSalleData}
        onSearch={handleSearchSalles}
      />

      {loadingSalle ? (
        <Box display="flex" justifyContent="center" mt={5}>
          <CircularProgress />
        </Box>
      ) : (
        sallesDisponibles.map((salle) => (
          <Paper
            key={salle.id}
            elevation={3}
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              p: 2,
              mb: 2,
              borderRadius: 2,
              backgroundColor:"#ebf8ffff"
            }}
          >
            {/* Icone */}
            <Box display="flex" alignItems="center" gap={2}>
              <AccountBalanceTwoToneIcon sx={{ fontSize: 40, color: '#3b82f6' }} />

              {/* Infos de la salle */}
              <Box>
                <Typography variant="subtitle2">
                  <strong>ID:</strong> {salle.id}
                </Typography>
                <Typography variant="subtitle2">
                  <strong>Capacité:</strong> {salle.nbPlaces}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Description : {salle.description || "Aucune description"}
                </Typography>
              </Box>
            </Box>

            {/* Bouton réserver */}
            <Button 
              variant="contained" 
              sx={{backgroundColor:"#0ce22fff", color:"white"}}
              onClick={() => handleReservSalle(salle)}  
            >
              RÉSERVER
            </Button>
          </Paper>
        ))
      )}

      <Typography variant="h4" fontWeight="bold" color='#3b82f6' textAlign="center" mb={3}>
        MATÉRIEL PÉDAGOGIQUE
      </Typography>

      <Typography variant="h6" fontWeight="bold" color='#3b82f6' mb={3}>
        ORDINATEUR PORTABLE
      </Typography>

      <ReservForm
        title="Reserve Data"
        formData={reservOrdinateurData}
        setFormData={setReservOrdinateurData}
        onSearch={handleSearchOrdinateurs}
      />

      {loadingOrdi ? (
        <Box display="flex" justifyContent="center" mt={5}>
          <CircularProgress />
        </Box>
      ) : (
        ordinateursDisponibles.map((ordinateur) => (
          <Paper
            key={ordinateur.id}
            elevation={3}
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              p: 2,
              mb: 2,
              borderRadius: 2
            }}
          >
            {/* Icone */}
            <Box display="flex" alignItems="center" gap={2}>
              <ComputerIcon sx={{ fontSize: 40, color: '#3b82f6' }} />

              {/* Infos de la salle */}
              <Box>
                <Typography variant="subtitle2">
                  <strong>nom:</strong> {ordinateur.nom}
                </Typography>
                <Typography variant="subtitle2">
                  <strong>marque et modèle:</strong> {ordinateur.marque} - {ordinateur.model}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Description : <strong>capacité-</strong>{ordinateur.stockage}Go <strong>ram-</strong>{ordinateur.memoire}Go <strong>processeur-</strong>{ordinateur.processeur}
                </Typography>
              </Box>
            </Box>

            {/* Bouton réserver */}
            <Button 
              variant="contained" 
              sx={{backgroundColor:"#0ce22fff", color:"white"}}
              onClick={() => handleReservationMateriel(ordinateur, "ordinateur")} 
            >
              RÉSERVER
            </Button>
          </Paper>
        ))
      )}

      <Typography variant="h6" fontWeight="bold" color='#3b82f6' mb={3}>
        VIDÉO PROJECTEUR
      </Typography>

      <ReservForm
        title="Reserve Data"
        formData={reserVideoProjData}
        setFormData={setReserVideoProjData}
        onSearch={handleSearchVideoProjs}
      />

      {loadingVideoProj ? (
        <Box display="flex" justifyContent="center" mt={5}>
          <CircularProgress />
        </Box>
      ) : (
        videoProjsDisponibles.map((videoProj) => (
          <Paper
            key={videoProj.id}
            elevation={3}
            sx={{
              display: 'flex',
              justifyContent: 'space-between',
              alignItems: 'center',
              p: 2,
              mb: 2,
              borderRadius: 2
            }}
          >
            {/* Icone */}
            <Box display="flex" alignItems="center" gap={2}>
              <AlbumTwoToneIcon sx={{ fontSize: 40, color: '#3b82f6' }} />

              {/* Infos de la salle */}
              <Box>
                <Typography variant="subtitle2">
                  <strong>ID:</strong> {videoProj.nom}
                </Typography>
                <Typography variant="subtitle2">
                  <strong>marque et modèle:</strong> {videoProj.marque} - {videoProj.model}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                  Description : <strong>résolution-</strong>{videoProj.resolution} <strong>fréquence-</strong>{videoProj.frequence}Hz <strong>lampe-</strong>{videoProj.type_de_lampe}
                </Typography>
              </Box>
            </Box>

            {/* Bouton réserver */}
            <Button 
              variant="contained" 
              sx={{backgroundColor:"#0ce22fff", color:"white"}}
              onClick={() => handleReservationMateriel(videoProj, "videoProj")} 
            >
              RÉSERVER
            </Button>
          </Paper>
        ))
      )}
    </Box> 
  );
}
