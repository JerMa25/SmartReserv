import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Box } from '@mui/material';
import Sidebar from './Sidebar';
import Bienvenue from './Bienvenue';
import Programme from '../components/Programme';
import ConsRecapitulatifHoraire from './ConsRecapitulatifHoraire';
import EditRecapitulatifHoraire from './responsableView/EditRecapitulatifHoraire';
import MesReservations from './MesReservations';
import NouvelleReservation from './NouvelleReservation';
import Headerbar from './Headerbar';

export default function Dashboard() {
  const navigate = useNavigate();
  const [vueActive, setVueActive] = useState('bienvenue');

  // Redirection si non connecté
  useEffect(() => {
    if(!localStorage.getItem("utilisateur")){
      navigate("/connexion");
    }
  }, [navigate]); // Ajout de la dépendance navigate

   // Fonction pour changer la vue
  const renderContent = () => {
    switch (vueActive) {
      case 'mes-reservations':
        return <MesReservations />;
      case 'nouvelle-reservation':
        return <NouvelleReservation />;
      case 'programme':
        return <Programme />;
      case 'consulter-recap-horaire':
        return <ConsRecapitulatifHoraire />;
      case 'editer-recap-horaire':
        return <EditRecapitulatifHoraire />;
      default:
        return <Bienvenue />;
    }
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <Sidebar onChangeVue={setVueActive} />
      <Box sx={{ flexGrow: 1 }}>
        <Headerbar vue={vueActive}/>
        {renderContent()}
      </Box>
    </Box>
  );
}