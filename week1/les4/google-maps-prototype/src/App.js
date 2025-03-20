import './index.css';
import React from 'react';
import Map from './components/Map.jsx';
import 'mapbox-gl/dist/mapbox-gl.css';
import { useState } from 'react';
import Dropdown from './components/Dropdown.jsx';

function App() {
  const [currentMap, setCurrentMap] = useState('')
  
  return (
    <div>
      <Dropdown setCurrentMap={setCurrentMap} />
      <Map currentMap={currentMap} />
    </div>
  );
}

export default App;
