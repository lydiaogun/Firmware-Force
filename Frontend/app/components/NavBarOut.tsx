"use client";

/* NavBar that dynamically changes based on whether the user is signed in or not.
 If the user is signed in, it will show the regular nav bar, 
 if the user is not signed in, it will show the signed out nav bar. */

import { useRouter } from "next/navigation";

export default function NavBarOut() {

    const router = useRouter();

    return (
            <nav aria-label="Main navigation" className="flex flex-row items-center justify-between w-full p-2 bg-white dark:bg-black">
                <a href="#main-content" 
                    className="sr-only focus:not-sr-only focus:absolute focus:top-0 focus:left-0 bg-white text-[#2F4F4F] p-2">
                    Skip to main content
                 </a>
                <img src ="/CommunityFixLogo.png" alt= "Community Fix Logo" width={200} height={60} />
                {/* Home Button, About us, Dashboard, Quick Report Button, Sign in and Register buttons */}
                <div className="flex flex-row space-x-4">
                    <button onClick={() => router.push('/')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> Home </button>
                    <button onClick={() => router.push('/reportPage')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> Quick Report </button>
                    <button onClick={() => router.push('/signInPage')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> Sign in </button>
                    <button onClick={() => router.push('/register')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> Register </button>
                    <button onClick={() => router.push('/aboutUs')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> About Us </button>
                </div>  
            </nav>
        );
    }
