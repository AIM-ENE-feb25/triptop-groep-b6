import ReactMapboxGl, {Layer, Feature} from 'react-mapbox-gl'

import 'mapbox-gl/dist/mapbox-gl.css'

export default function Map({currentMap}) {

    const ShowMap = ReactMapboxGl({
        accessToken: process.env.REACT_APP_MAPBOX_PUBLIC_TOKEN
    })

    return (
        <ShowMap
            // eslint-disable-next-line react/style-prop-object
            style={`mapbox://styles/mapbox/${currentMap !== '' ? currentMap : 'dark-v11'}`}
            containerStyle={{
                height: '100vh',
                width: '100vw'
            }}
        >
            <Layer type="symbol" id="marker" layout={{ 'icon-image': 'marker-15' }}>
                <Feature coordinates={[51.988935616137546, 5.949077539183006]} />
            </Layer>

        </ShowMap>
    )

}