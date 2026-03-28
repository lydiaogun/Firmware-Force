"use client";

import DynamicNavBar from '../components/NavBarInA';
import DashP from '../components/dashP';
import DashV from '../components/dashV';

export default function DashboardPage() {

  return (
      <>
          <header>

              <DynamicNavBar />

          </header>

          <main className = "flex flex-row items-center justify-center bg-[url(../public/CommunityFixLandingPageImage.png)] h-screen w-screen gap-10 min-h-screen">
            
            <div className = "flex flex-row items-center justify-center space-x-20 backdrop-blur-xs w-full min-h-screen">

                <div className = "flex flex-col items-center"> 

                    <header className = "text-[#2F4F4F] text-[22px] underline">
                        
                        Critical reports:
                        
                    </header>

                    <section aria-label="dashboard panel">

                        <DashP />

                    </section>

                </div>

                <div className = "flex flex-col items-center">

                    <header className = "text-[#2F4F4F] text-[22px] underline">
                    
                        Community voice:
                    
                    </header>

                    <section aria-label="Visual dashboard panel">

                        <DashV />

                    </section>

                </div>

            </div>

          </main>
      </>
  );
}
