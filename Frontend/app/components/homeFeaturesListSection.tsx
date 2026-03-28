/* 
1x4 horizontal flex grid of (hover highlightable) clickable cards with their associated icons representing issue categories: 

- Waste & Environmental 
This category handles sanitation, aesthetic degradation, and public health concerns.

- Road & Vehicle Infrastructure 
This focuses strictly on the carriageway and traffic management systems.

- Pavement & Pedestrian Infrastructure
Isolates issues affecting foot traffic and accessibility, keeping it distinct from road vehicular issues

- Public Safety & Hazards
*/

export default function HomeFeaturesListSection() {
    return (
        <div className="flex flex-col items-center justify-center w-full p-20 bg-gray-100 dark:bg-gray-900">
            <h3 className="text-lg font-bold text-gray-800 dark:text-gray-200 mb-4">Issues</h3>
            <h1 className="text-5xl font-bold text-gray-800 dark:text-gray-200 mb-4 align-middle ">Four ways to help!</h1>
            <h3 className="text-lg font-bold text-gray-800 dark:text-gray-200 mb-4">Pick a category and report what you see:</h3>
            <div className="grid grid-cols-4 gap-4">
                <button className="flex flex-col items-center bg-[#2F4F4F] text-white p-4 rounded hover:bg-[#263D3D] transition colours">
                    <img src="/Waste.png" alt="Waste & Environmental" className="w-25 h-25 mb-2" />
                    Waste & Environmental
                    <p className="text-sm text-center">Bins overflowing, rubbish scattered, filth</p>
                </button>
                <button className="flex flex-col items-center bg-[#2F4F4F] text-white p-4 rounded hover:bg-[#263D3D] transition colours">
                    <img src="/Road.png" alt="Road & Vehicle Infrastructure" className="w-25 h-25 mb-2" />
                    Road & Vehicle Infrastructure
                    <p className="text-sm text-center">Broken carriageway affecting traffic flow</p>
                </button>
                <button className="flex flex-col items-center bg-[#2F4F4F] text-white p-4 rounded hover:bg-[#263D3D] transition colours">
                    <img src="/Pavement.png" alt="Pavement & Pedestrian Infrastructure" className="w-25 h-25 mb-2" />
                    Pavement & Pedestrian Infrastructure
                    <p className="text-sm text-center">Cracked surfaces, barriers, mobility issues.</p>
                </button>
                <button className="flex flex-col items-center bg-[#2F4F4F] text-white p-4 rounded hover:bg-[#263D3D] transition colours">
                    <img src="/Safety.png" alt="Public Safety & Hazards" className="w-25 h-25 mb-2" />
                    Public Safety & Hazards
                    <p className="text-sm text-center">Dangers threatening your neighbourhood.</p>
                </button>
            </div>
        </div>

    )
}