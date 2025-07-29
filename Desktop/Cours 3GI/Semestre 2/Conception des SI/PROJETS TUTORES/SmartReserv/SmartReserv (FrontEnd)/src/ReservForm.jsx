// components/ReservForm.jsx
import React from 'react';
import { Accordion, Button, AccordionSummary, AccordionDetails, Box, TextField, Typography } from '@mui/material';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

const horairesOptions = Array.from({ length: (22 - 6) / 2 }, (_, i) => {
  const h = 6 + i * 2;
  const start = `${h.toString().padStart(2, '0')}:00`;
  const end = `${(h + 2).toString().padStart(2, '0')}:00`;
  return `${start} - ${end}`;
});

export default function ReservForm({ title, type, formData = {}, setFormData, onSearch }) {
  const today = new Date().toISOString().split('T')[0];

  // Initialize form data with default values
  const safeFormData = {
    date: formData.date || today,
    horaire: formData.horaire || '',
    capacite: type === 'salle' ? formData.capacite || '' : undefined,
    marque: type !== 'salle' ? formData.marque || '' : undefined,
    ...formData
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ 
      ...prev, 
      [name]: value 
    }));
  };

  return (
    <Accordion sx={{ mb: 2 }}>
      <AccordionSummary
        expandIcon={<ExpandMoreIcon />}
        aria-controls="filter-options-content"
        id="filter-options-header"
        sx={{ color: 'white', backgroundColor: '#3b82f6' }}
      >
        <Typography fontWeight="bold">{title}</Typography>
      </AccordionSummary>

      <AccordionDetails sx={{ backgroundColor: '#d8e5f8ff' }}>
        <Box display="flex" flexDirection="column" gap={2}>
          <TextField
            fullWidth
            sx={{ backgroundColor: '#fff' }}
            size="small"
            label="Date"
            name="date"
            type="date"
            inputProps={{
              min: today,
              onKeyDown: (e) => e.preventDefault(),
            }}
            InputLabelProps={{ shrink: true }}
            value={safeFormData.date}
            onChange={handleChange}
          />

          <TextField
            fullWidth
            select
            size="small"
            name="horaire"
            SelectProps={{ native: true }}
            value={safeFormData.horaire}
            onChange={handleChange}
            sx={{ backgroundColor: '#fff' }}
          >
            <option value="">Choisir un horaire</option>
            {horairesOptions.map((horaire, i) => (
              <option key={i} value={horaire}>{horaire}</option>
            ))}
          </TextField>

          {type === 'salle' && (
            <TextField
              fullWidth
              size="small"
              label="Capacité minimale (optionnelle)"
              name="capacite"
              type="number"
              value={safeFormData.capacite}
              onChange={handleChange}
              sx={{ backgroundColor: '#fff' }}
            />
          )}

          {type !== 'salle' && (
            <TextField
              fullWidth
              size="small"
              label="Marque (optionnelle)"
              name="marque"
              value={safeFormData.marque}
              onChange={handleChange}
              sx={{ backgroundColor: '#fff' }}
            />
          )}

          {onSearch && (
            <Button 
              variant="contained" 
              size="small" 
              sx={{
                backgroundColor: '#9418dbff',
                textTransform: 'none',
                marginLeft: 'auto',
                display: 'block'
              }}
              onClick={onSearch}
            >
              Search
            </Button>
          )}
        </Box>
      </AccordionDetails>
    </Accordion>
  );
}