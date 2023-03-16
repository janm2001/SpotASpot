import { useState } from 'react'
import reactLogo from './assets/react.svg'
import './App.css'
import Navbar from './components/Navbar/Navbar'
import { Routes, Route, Outlet, Link } from "react-router-dom";
import Home from './pages/Home/Home';
import Events from './pages/Events/Events';
import MyEvents from './pages/MyEvents/MyEvents';
import AboutUs from './pages/AboutUs/AboutUs';

function App() {
  

  return (
    <>
    <Navbar />
    <Routes>
      <Route path="/" element={<Home />}></Route>
      <Route path="/events" element={<Events />}></Route>
      <Route path="/myevents" element={<MyEvents />}></Route>
      <Route path="/about" element={<AboutUs />}></Route>

    </Routes>

    </>
    
    
  )
}

export default App
