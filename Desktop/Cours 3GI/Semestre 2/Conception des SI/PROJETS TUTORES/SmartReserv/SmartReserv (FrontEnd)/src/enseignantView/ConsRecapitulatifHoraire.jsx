import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
  Box, Typography, Table, TableBody, TableCell, TableContainer,
  TableHead, TableRow, Paper, Button
} from '@mui/material';
import AccessTimeIcon from '@mui/icons-material/AccessTime';
import ArrowBackIosNewIcon from '@mui/icons-material/ArrowBackIosNew';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

const jours = ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi', 'Samedi'];

const horaires = Array.from({ length: (22 - 6) / 2 }, (_, i) => {
  const heure = 6 + i * 2;
  const start = `${heure.toString().padStart(2, '0')}:00`;
  const end = `${(heure + 2).toString().padStart(2, '0')}:00`;
  return `${start} - ${end}`;
});

// Fonction utilitaire pour obtenir le début de semaine
const getStartOfWeek = (date) => {
  const day = date.getDay() -1;
  const mondayOffset = day === 0 ? -6 : 1 - day;
  const monday = new Date(date);
  monday.setDate(date.getDate() + mondayOffset);
  monday.setHours(0, 0, 0, 0);
  return monday;
};

const getWeekDates = (monday) => {
  return jours.map((_, i) => {
    const d = new Date(monday);
    d.setDate(monday.getDate() + i);
    return d.toISOString().split('T')[0];
  });
};

export default function ConsRecapitulatifHoraire() {
  const [planning, setPlanning] = useState([]);
  const [currentWeekStart, setCurrentWeekStart] = useState(getStartOfWeek(new Date()));
  const utilisateur = JSON.parse(localStorage.getItem("utilisateur"));

  const weekDates = getWeekDates(currentWeekStart);

  useEffect(() => {
    axios.get('http://localhost:8080/api/planning')
      .then((res) => {
        setPlanning(res.data);
      })
      .catch((err) => {
        console.error("Erreur lors de la récupération du planning :", err);
      });
  }, []);

  const getCoursForCell = (date, horaire) => {
    return planning.filter((p) => p.date === date && p.horaire === horaire);
  };

  return (
    <Box p={3} sx={{'height':'100vh'}}>
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={3}>
        <Button
          variant="outlined"
          startIcon={<ArrowBackIosNewIcon />}
          onClick={() =>
            setCurrentWeekStart((prev) => {
              const newDate = new Date(prev);
              newDate.setDate(prev.getDate() - 7);
              return newDate;
            })
          }
        >
          Semaine précédente
        </Button>

        <Typography variant="h6" fontWeight="bold">
          Semaine du {weekDates[0]} au {weekDates[weekDates.length - 1]}
        </Typography>

        <Button
          variant="outlined"
          endIcon={<ArrowForwardIosIcon />}
          onClick={() =>
            setCurrentWeekStart((prev) => {
              const newDate = new Date(prev);
              newDate.setDate(prev.getDate() + 7);
              return newDate;
            })
          }
        >
          Semaine suivante
        </Button>
      </Box>

      <Typography variant="subtitle2" color="text.secondary" gutterBottom>
        Récapitulatif Horaire: <strong style={{ color: '#1789d4ff' }}>{utilisateur.nom}</strong>
      </Typography>

      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 1000 }} size="small">
          <TableHead>
            <TableRow>
              <TableCell><strong>Horaire</strong></TableCell>
              {jours.map((jour, index) => (
                <TableCell key={index} align="center">
                  <Typography variant="subtitle2">
                    {jour} <br />
                    <small>{weekDates[index]}</small>
                  </Typography>
                </TableCell>
              ))}
            </TableRow>
          </TableHead>

          <TableBody>
            {horaires.map((horaire, rowIdx) => (
              <TableRow key={rowIdx}>
                <TableCell>
                  <Box display="flex" alignItems="center" gap={1}>
                    <AccessTimeIcon fontSize="small" color="action" />
                    <Typography>{horaire}</Typography>
                  </Box>
                </TableCell>

                {weekDates.map((date, colIdx) => {
                  const cours = getCoursForCell(date, horaire);
                  return (
                    <TableCell key={colIdx} align="left" sx={{ padding: 1 }}>
                      {cours 
                        .filter((c) => utilisateur && utilisateur.nom === c.nomEns)
                        .map((c, i) => (
                        <Box key={i} mb={0.5} p={1} sx={{
                          backgroundColor: '#e0f2fe',
                          borderRadius: 1,
                          boxShadow: 1,
                        }}>
                          <Typography variant="body2" fontWeight="bold">
                            {c.formation}
                          </Typography>
                          <Typography variant="caption">
                            <strong>Ens:</strong>{c.nomEns} - <strong>Salle No: </strong>{c.classId}
                          </Typography>
                        </Box>
                      ))}
                    </TableCell>
                  );
                })}
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
}
