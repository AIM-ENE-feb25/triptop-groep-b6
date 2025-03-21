import './index.css';
import React from 'react';
import Map from './components/Map.jsx';
import 'mapbox-gl/dist/mapbox-gl.css';
import { useState } from 'react';
import Dropdown from './components/Dropdown.jsx';
import Goto from './components/Goto.jsx';

function App() {
  const [currentMap, setCurrentMap] = useState('')
  
  return (
    <div>
      <div className='information'>
        <Goto />
        <Dropdown setCurrentMap={setCurrentMap} />
      </div>
      <Map currentMap={currentMap} />
    </div>
  );
}

export default App;
