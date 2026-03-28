"use client";

import { ChangeEvent, useState } from 'react';
import { useRouter } from 'next/navigation';
import { createSession } from '../actions';
import { apiUrl } from '@/lib/api';

export default function SignIn() {

    const router = useRouter();

    const [textU, setTextU] = useState("");
    
    const handleChangeU = (event: ChangeEvent<HTMLInputElement>) => {
        setTextU(event.target.value)
    }

    const [textP, setTextP] = useState("");
      
    const handleChangeP = (event: ChangeEvent<HTMLInputElement>) => {
        setTextP(event.target.value)
    }

    const [isVisible, setIsVisible] = useState(false);

    const [submitMessage, setSubmitMessage] = useState("");

    const handleSubmit = ( async () => {
        if (!textU || !textP) {
        setSubmitMessage("Please complete all sections before attempting to sign in.");
        return;
        }

        try {
            const response = await fetch(apiUrl(`/api/users/signin/${textU}/${textP}`), {
                method: 'POST'
            });

            const data = await response.json();

            if (response.ok && data.token) {

                await createSession(data.token, data.userRole, data.userId);
                
                if (data.userRole === 'ADMIN') {
                    router.push('/dashboardPage');
                } else if (data.userRole === 'COMMUNITY') {
                    router.push('/communityPage');
                } else {
                    router.push('/SignInPage');
                }
            } else {
                setSubmitMessage("Login failed.");
            }
        } catch {
            setSubmitMessage("Login failed.");
        }
    })

    return (
        <div className = "flex flex-col items-center space-y-10 border-2 border-[#2F4F4F] p-15 bg-white rounded">

            <header className = "text-[#2F4F4F] text-[36px] underline">

                Sign in:

            </header>

            <div className = "flex flex-col items-center space-y-4 text-[#2F4F4F] text-[1.4rem] underline">

                <label htmlFor = "username-input"> Enter your username: </label>

                <input className = "border-2 border-[#2F4F4F]" id = "username-input" type = "text" value = {textU} onChange = {handleChangeU} placeholder = "Enter username..." />

            </div>

            <div className = "flex flex-col items-center space-y-4 text-[#2F4F4F] text-[1.4rem] underline">

                <label htmlFor = "password-input"> Enter your password: </label>

                <input className = "border-2 border-[#2F4F4F]" id = "password-input" type = {isVisible ? "text" : "password"} value = {textP} onChange = {handleChangeP} placeholder = "Enter password..." />

                <button onClick={() => setIsVisible(!isVisible) } aria-label={isVisible ? "Hide password" : "Show password"} className = "flex flex-col bg-[#2F4F4F] text-white items-center p-1 border rounded hover:bg-[#263D3D] transition-colors" type = "button"> <img src = "/Visibility.jpg" alt = "" className = "w-7.5 h-7.5"/>   </button>

            </div>

            <button onClick = {handleSubmit} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition colours"> Sign in </button>

            {submitMessage}

        </div>
    );
}
