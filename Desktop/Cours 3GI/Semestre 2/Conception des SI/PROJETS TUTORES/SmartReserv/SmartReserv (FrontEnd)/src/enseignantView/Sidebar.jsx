import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Box, Button, Stack, Typography, IconButton } from '@mui/material';
import { KeyboardArrowDown, KeyboardArrowUp } from '@mui/icons-material';
import LogoutIcon from '@mui/icons-material/Logout'

export default function Sidebar({ onChangeVue }) {
  // Récupère l'utilisateur depuis le localStorage
  const utilisateur = JSON.parse(localStorage.getItem("utilisateur"));
  // Vérifie s'il est responsable
  const isResponsable = utilisateur?.isResponsable;
  const navigate = useNavigate();
  const [showReservationButtons, setShowReservationButtons] = useState(false);
  const [showRecapButtons, setShowRecapButtons] = useState(false);
  const handleLogout = () => {
  localStorage.removeItem("utilisateur");
  navigate("/connexion");
};

  const toggleReservationButtons = () => {
    setShowReservationButtons(!showReservationButtons);
  };

  const toggleRecapButtons = () => {
    setShowRecapButtons(!showRecapButtons);
  };

  return (
    <Box
      sx={{
        width: 250,
        height: '120vh',
        minHeight: '100%',
        display: 'flex',
        flexDirection: 'column',
        bgcolor: '#1e3a8a',
        color: 'white',
        padding: 2,
        overflowY: 'auto',
      }}
    >
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <Typography variant="h6" fontWeight="bold" mb={2}>
          RÉSERVATIONS
        </Typography>
        <IconButton
          onClick={toggleReservationButtons}
          sx={{ color: 'white' }}
          aria-label="toggle-reservations"
        >
          {showReservationButtons ? <KeyboardArrowDown /> : <KeyboardArrowUp />}
        </IconButton>
      </Box>

      <Box
        sx={{
          overflow: 'hidden',
          transition: 'max-height 0.3s ease',
          maxHeight: showReservationButtons ? '100px' : '0',
        }}
      >
        <Stack spacing={1}>
          <Button
            fullWidth
            variant="text"
            sx={{
              justifyContent: 'flex-start',
              color: 'white',
              bgcolor: 'transparent',
              '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
            }}
            onClick={() => onChangeVue('mes-reservations')}
          >
            Mes réservations
          </Button>

          <Button
            fullWidth
            variant="text"
            sx={{
              justifyContent: 'flex-start',
              color: 'white',
              bgcolor: 'transparent',
              '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
            }}
            onClick={() => onChangeVue('nouvelle-reservation')}
          >
            Nouvelle réservation
          </Button>
        </Stack>
      </Box>

      
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <Typography variant="h6" fontWeight="bold" mb={2}>
          RÉCAPITULATIF HORAIRE
        </Typography>
        <IconButton
          onClick={toggleRecapButtons}
          sx={{ color: 'white' }}
          aria-label="toggle-recap"
        >
          {showRecapButtons ? <KeyboardArrowDown /> : <KeyboardArrowUp />}
        </IconButton>
      </Box>

      
      <Box
        sx={{
          overflow: 'hidden',
          transition: 'max-height 0.3s ease',
          maxHeight: showRecapButtons ? '100px' : '0',
        }}
      >
        <Stack spacing={1}>
          <Button
            fullWidth
            variant="text"
            sx={{
              justifyContent: 'flex-start',
              color: 'white',
              bgcolor: 'transparent',
              '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
            }}
            onClick={() => onChangeVue('consulter-recap-horaire')}
          >
            Consultation
          </Button>

          <Button
            fullWidth
            variant="text"
            sx={{
              justifyContent: 'flex-start',
              color: 'white',
              bgcolor: 'transparent',
              visibility: isResponsable ? 'visible' : 'hidden',
              '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
            }}
            onClick={() => onChangeVue('editer-recap-horaire')}
          >
            Édition
          </Button>
        </Stack>
      </Box>
          
      <Stack spacing={1}>
        <Button
          fullWidth
          variant="text"
          sx={{
            justifyContent: 'flex-start',
            color: 'white',
            bgcolor: 'transparent',
            '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
          }}
          onClick={() => onChangeVue('programme')}
        >
          Emploi du temps
        </Button>
      </Stack>

      <Stack spacing={1}>
        <Button
          fullWidth
          variant="text"
          startIcon={<LogoutIcon />}
          sx={{
            justifyContent: 'flex-start',
            color: 'white',
            bgcolor: 'transparent',
            '&:hover': { bgcolor: 'rgba(255,255,255,0.1)' },
            mt: 2, // optional spacing from previous button
          }}
          onClick={handleLogout}
        >
          Déconnexion
        </Button>
      </Stack>
    </Box>
  );
}