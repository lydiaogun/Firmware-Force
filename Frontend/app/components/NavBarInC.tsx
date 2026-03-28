"use client";

import { useRouter } from "next/navigation";

export default function NavBarIn() {

    const router = useRouter();

    return (
        <nav aria-label="Main navigation" className="flex flex-row items-center justify-between w-full p-2 bg-white dark:bg-black">
                <a href="#main-content" 
                    className="sr-only focus:not-sr-only focus:absolute focus:top-0 focus:left-0 bg-white text-[#2F4F4F] p-2">
                    Skip to main content
                 </a>
                <img src ="/CommunityFixLogo.png" width={200}  alt = "Community Fix Logo" height={60} />
                <div className="flex flex-row space-x-4">
                    <button onClick={() => router.push('/communityPage')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition-colors"> Home </button>
                    <button onClick={() => router.push('/')} className="bg-[#2F4F4F] text-white px-4 py-2 rounded hover:bg-[#263D3D] transition colours"> Sign out </button>
                 </div>
            </nav>
        );
}
