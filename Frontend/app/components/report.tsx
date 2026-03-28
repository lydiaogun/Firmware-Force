"use client";

import { useState } from 'react';
import Select from 'react-select';
import { apiUrl } from '@/lib/api';

export default function Report() {

  const [selectedIssue, setSelectedIssue] = useState<{value: string, label: string} | null>(null);

  const dropDownData = {
    road: [
      { value: 'pothole', label: 'POTHOLE' },
      { value: 'obstruction', label: 'OBSTRUCTION' },
      { value: 'crash', label: 'CRASH' }
    ],
    waste: [
      { value: 'overflowing_bin', label: 'OVERFLOWING_BIN' },
      { value: 'fly_tipping', label: 'FLY_TIPPING' },
      { value: 'sewage_spill', label: 'SEWAGE_SPILL' }
    ],
    pavement: [
      { value: 'cracks', label: 'CRACKS' },
      { value: 'loose_tile', label: 'LOOSE_TILE' },
      { value: 'hazard', label: 'HAZARD' }
    ],
    safety: [
      { value: 'illegal_activity', label: 'ILLEGAL_ACTIVITY' },
      { value: 'gathering', label: 'LARGE_GROUP_GATHERING' },
      { value: 'antisocial', label: 'ANTISOCIAL_BEHAVIOUR' }
    ]
  };

  const [selectedOptions, setSelectedOptions] = useState<{value: string, label: string}[]>([]);

  const [categoryMessage, setCategoryMessage] = useState("");

  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);

  const handleButtonClick = (category: keyof typeof dropDownData) => {

    setSubmitMessage("");

    setSelectedOptions(dropDownData[category]);

    setCategoryMessage(`${category} category selected`);

    setSelectedCategory(category);
  };

  const [submitMessage, setSubmitMessage] = useState("");

  const [placeHolder] = useState("Select an issue...");

  const handleSubmit = ( async () => {
    if (!selectedCategory || !selectedIssue || !coordinates) {
      setSubmitMessage("Please complete all sections before attempting to submit.");
      return;
    }

    const reportData = {
      location: coordinates,
      category: selectedCategory.toUpperCase(),
      issue: selectedIssue.value.toUpperCase(),
      userId: null,
      votes: 0
    };

    try {
      const response = await fetch(apiUrl('/api/reports/createReport'), {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(reportData),
      });

      if (response.ok) {
        setSubmitMessage("Report submitted");
        window.location.reload();
      } else {
        setSubmitMessage("Failed to submit report");
      }
    } catch {
      setSubmitMessage("Error connecting to server");
    }

  })

  const [coordinates, setCoordinates] = useState("");

  const [coordMessage, setCoordMessage] = useState("");

  const handleLocation = () => {
    if (navigator.geolocation) 
    {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const {longitude, latitude} = position.coords
          setCoordinates(`${longitude.toFixed(6)} , ${latitude.toFixed(6)}`)
          setCoordMessage("Location retrieved")
        },
        () => {
          setCoordMessage("Error retrieving coordinates")
        }
      )
    }
    else
    {
      setCoordMessage("Browser does not support geolocation")
    }
  }

  return (
    <div className="flex flex-col items-center border-2 border-[#2F4F4F] bg-white dark:bg-black underline text-[#2F4F4F] text-[1.375rem]">

        <div className="flex flex-row items-center bg-white dark:bg-black underline text-[#2F4F4F] text-[1.375rem]">

          <div className="flex flex-col items-center p-20">

            <header className="text-[#2F4F4F] text-[1.375rem] underline">

              Choose a Category:

            </header>

            <div className="grid grid-cols-2 gap-2 p-2 border-2 border-[#2F4F4F]">

              <button onClick={() => handleButtonClick('road')}

                className={`flex flex-col text-white items-center gap-2 p-2 border rounded transition-colors ${selectedCategory === 'road' ? '!bg-[#1a3333]' : 'bg-[#2F4F4F] hover:bg-[#263D3D]'}`}>

                <img src="/Road.png" alt="Road" className="w-40 h-40"/>

                <span>Road</span>

              </button>

              <button onClick={() => handleButtonClick('waste')}

                className={`flex flex-col text-white items-center gap-2 p-2 border rounded transition-colors ${selectedCategory === 'waste' ? '!bg-[#1a3333]' : 'bg-[#2F4F4F] hover:bg-[#263D3D]'}`}>

                <img src="/Waste.png" alt="Waste" className="w-40 h-40"/>

                <span>Waste</span>

              </button>

              <button onClick={() => handleButtonClick('pavement')}

                className={`flex flex-col text-white items-center gap-2 p-2 border rounded transition-colors ${selectedCategory === 'pavement' ? '!bg-[#1a3333]' : 'bg-[#2F4F4F] hover:bg-[#263D3D]'}`}>

                <img src="/Pavement.png" alt="Pavement" className="w-40 h-40"/>

                <span>Pavement</span>

              </button>

              <button onClick={() => handleButtonClick('safety')}

                className={`flex flex-col text-white items-center gap-2 p-2 border rounded transition-colors ${selectedCategory === 'safety' ? '!bg-[#1a3333]' : 'bg-[#2F4F4F] hover:bg-[#263D3D]'}`}>

                <img src="/Safety.png" alt="Safety" className="w-40 h-40"/>

                <span>Safety</span>

              </button>

            </div>

            <div role="alert" aria-live="assertive">

              {categoryMessage}

            </div>

          </div>

          <div className="flex flex-col items-center space-y-10 justify-between p-20">

            <div className="flex flex-col space-y-2 items-center">

              <header className="text-[#2F4F4F] text-[1.375rem] underline">

                Choose an Issue:

              </header>

              <Select instanceId="issue-select" aria-label="Choose an issue" className="border-2 border-[#2F4F4F]" onChange={(newValue) => setSelectedIssue(newValue)} options={selectedOptions} isSearchable={true} placeholder = {placeHolder}/>

            </div>

            <button onClick={handleLocation} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors">Use current Location</button>
            
            <div role="alert" aria-live="assertive">

              {coordMessage}

            </div>

            <button onClick={handleSubmit} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors">Submit</button>

            <div role="alert" aria-live="assertive">

              {submitMessage}

            </div>
            
          </div>

        </div>

    </div>
  );
}
