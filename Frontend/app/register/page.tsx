"use client";

import DynamicNavBar from '../components/dynamicNavBar';
import Register from '../components/register';

export default function RegisterPage() {

    return (
        <div className = "bg-[url(../public/CommunityFixLandingPageImage.png)]">

            <header>

                <DynamicNavBar />
                
            </header>

            <main id = "main-content" className = "flex flex-col space-y-10 backdrop-blur-xs w-full min-h-screen items-center min-h-screen">

                <section aria-label="Registration section" className="flex flex-col items-center py-10">

                    <Register />

                </section>

            </main>
        </div>
    );
}
