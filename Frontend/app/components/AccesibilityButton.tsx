"use client";
import { useState, useEffect } from 'react';

export default function AccesibilityButton(){
    const [open, setOpen] = useState(false);
    const [dyslexia, setDyslexia] = useState(false);

    useEffect(() => {
        document.documentElement.classList.toggle("dyslexia", dyslexia);}, [dyslexia]);


    return (
      <div className = "fixed bottom-6 right-6 z-100">
        <button
           onClick = {() =>setOpen(prev => !prev)}
           className ="
             bg-blue-600 text-white
             p-4 rounded-full shadow-lg
             transition font-mono text-xl"
      >
            ♿
        </button>

        {open && (
            <div className = "mt-2 p-4 bg-white shadow-xl rounded-lg w-48 border text-black">
              <h3 className = "font-bold mb-2">Accessibility</h3>

              <button
                onClick={() => setDyslexia(prev => !prev)}
                className = "w-full text-left p-2 hover:bg-gray-100 rounded "
            >
                  Dyslexia-friendly font
              </button>
        </div>
    )}
    </div>
);
}
