// src/components/Header.jsx
import { AppBar, Toolbar, Button, Stack, Typography } from '@mui/material';
import { Link } from 'react-router-dom';
import SchoolIcon from '@mui/icons-material/School';

export default function Header() {
  return (
    <AppBar position="static" sx={{ 
      bgcolor: "white", 
      boxShadow: 'none', 
      borderBottom: '1px solid #e0e0e0'
    }}>
      <Toolbar>
        {/* App Name with Icon - Left Side */}
        <Typography 
          variant="h6" 
          component="div" 
          sx={{ 
            flexGrow: 1, 
            display: 'flex', 
            alignItems: 'center',
            color: '#3b82f6',
            fontWeight: 'bold'
          }}
        >
          <SchoolIcon sx={{ mr: 1 }} />
          SmartReserv
        </Typography>

        {/* Navigation Buttons - Right Side */}
        <Stack direction="row" spacing={2}>
        <Button 
            color="inherit" 
            sx={{ color: '#3b82f6' }}
            component={Link}
            to="/emploi_du_temps"
          >
            Emploi du temps
          </Button>
          <Button 
            color="inherit" 
            sx={{ color: '#3b82f6' }}
            component={Link}
            to="/connexion"
          >
            Connexion
          </Button>
        </Stack>
      </Toolbar>
    </AppBar>
  );
}