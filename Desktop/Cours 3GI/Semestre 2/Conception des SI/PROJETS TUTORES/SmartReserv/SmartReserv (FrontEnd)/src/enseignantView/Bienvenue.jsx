import React from 'react';
import EmojiEmotionsIcon from '@mui/icons-material/EmojiEmotions'; 
import { Box, Typography } from '@mui/material';
// ou import WelcomeIcon from '@mui/icons-material/WavingHand'; // Autre option d'icône

export default function Bienvenue() {
  return (
    <Box 
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          display: 'flex',
          justifyContent: 'center',
          alignItems: 'center',
          height: '110vh',
          background: 'linear-gradient(135deg, #52e1e6ff 0%, #1e40af 100%)'
        }}
      >
        <Box 
          sx={{
            textAlign: 'center',
            color: 'white',
            p: 4,
            borderRadius: 2,
            boxShadow: 3,
            maxWidth: 600,
            bgcolor: 'rgba(255, 255, 255, 0.1)',
            backdropFilter: 'blur(5px)'
          }}
        >
          <EmojiEmotionsIcon 
            sx={{ 
              fontSize: 60, 
              mb: 2,
              animation: 'wave 2s infinite',
              '@keyframes wave': {
                '0%': { transform: 'rotate(0deg)' },
                '10%': { transform: 'rotate(14deg)' },
                '20%': { transform: 'rotate(-8deg)' },
                '30%': { transform: 'rotate(14deg)' },
                '40%': { transform: 'rotate(-4deg)' },
                '50%': { transform: 'rotate(10deg)' },
                '60%': { transform: 'rotate(0deg)' },
                '100%': { transform: 'rotate(0deg)' }
              }
            }} 
          />
          <Typography 
            variant="h3" 
            component="h1"
            sx={{ 
              fontWeight: 'bold',
              mb: 2
            }}
          >
            Bienvenue sur SmartReserv
          </Typography>
          <Typography variant="h6">
            Prêt à gérer vos réservations ?
          </Typography>
        </Box>
      </Box>
  )
}
