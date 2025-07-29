import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Dashboard from './enseignantView/Dashboard';
import Connexion from './components/Connexion';
import ReactDOM from 'react-dom/client';
import { Toaster } from 'react-hot-toast';
import Emploi_du_temps from './components/Emploi_du_temps';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Dashboard />
  },
  {
    path: "/connexion",
    element: <Connexion />
  },
  {
    path: "/emploi_du_temps",
    element: <Emploi_du_temps />
  },
  {
  path: "*",
  element: <h1>404 - Page non trouvée</h1>
  }
])
ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <Toaster />
    <RouterProvider router={router}></RouterProvider>
  </React.StrictMode>,
)