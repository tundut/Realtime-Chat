
import { useState } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Chat from './components/Chat';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  const token = localStorage.getItem("token");
  const username = localStorage.getItem("username");

  const isAuth = !!token;

  return (
    <Routes>
      <Route
        path="/login"
        element={!isAuth ? <Login /> : <Navigate to="/chat" replace />}
      />

      <Route
        path="/register"
        element={!isAuth ? <Register /> : <Navigate to="/chat" replace />}
      />

      <Route
        path="/chat"
        element={isAuth ? <Chat username={username} /> : <Navigate to="/login" replace />}
      />

      <Route
        path="/"
        element={isAuth ? <Navigate to="/chat" replace /> : <Navigate to="/login" replace />}
      />

      <Route
        path="*"
        element={<Navigate to={isAuth ? '/chat' : '/login'} replace />}
      />
    </Routes>
  );
}

export default App;
