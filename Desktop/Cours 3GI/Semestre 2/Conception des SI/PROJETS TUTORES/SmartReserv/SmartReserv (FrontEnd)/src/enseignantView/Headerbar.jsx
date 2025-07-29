import React from 'react';
import { AppBar, Toolbar, Typography } from '@mui/material';
import SchoolIcon from '@mui/icons-material/School';

export default function Headerbar({ vue }) {
  let titre = '';
  switch (vue) {
    case 'mes-reservations':
      titre = 'Mes Réservations';
      break;
    case 'nouvelle-reservation':
      titre = 'Nouvelle Réservation';
      break;
    case 'programme':
      titre = 'Emploi du Temps';
      break;
    case 'consulter-recap-horaire':
      titre = 'Consulter Récapitulatif Horaire';
      break;
    case 'editer-recap-horaire':
      titre = 'Éditer Récapitulatif Horaire';
      break;
    default:
      null;
  }

  return (
    <AppBar position="static" color="primary" sx={{ mb: 2 }}>
      <Toolbar>
        <Typography 
          variant="h6" 
          component="div" 
          sx={{ 
            flexGrow: 1, 
            display: 'flex', 
            alignItems: 'center',
            color: '#f7f7f7ff',
            fontWeight: 'bold'
          }}
        >
          <SchoolIcon sx={{ mr: 1 }} />
          SmartReserv
        </Typography>
        <Typography
            variant="h6"
            component="div"
            sx={{
                position: 'absolute',
                left: '50%',
                transform: 'translateX(-50%)',
                fontWeight: 'bold',
                textAlign: 'center',
            }}
        >
            {titre}
        </Typography>
        {/* Tu peux ajouter ici des boutons, menus, etc. */}
      </Toolbar>
    </AppBar>
  );
}
