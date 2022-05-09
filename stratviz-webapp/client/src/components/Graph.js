import React from 'react'
import Plot from 'react-plotly.js'

// Graph contains all kinds of external control functionality, such as selecting which sensor data is plotted,
// from which car the data is plotted, which pre-processors are applied, the data range that is showed, from what 
// point the integrators are applied, etc.
// Something that could be added is a zoom on scroll
const Graph = () => {
    return (
        <div>
            <h1>Graph</h1>
            <Plot
                data={[
                    {
                        x: [1, 2, 3],
                        y: [1, 4, 9],
                        type: "scatter",
                        mode: "lines+markers",
                        marker: { color: 'red' }
                    },
                    // Can add multiple charts into one plot, e.g. add an additional barchart or something
                ]}
                layout={{ width: 720, height: 480, title: "A plot" }}
            />
        </div>
    )
}

export default Graph