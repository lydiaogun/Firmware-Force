"use client";

import DynamicNavbar from '../components/dynamicNavBar';
import Report from '../components/report'

export default function ReportPage() {

  return (
    <div className = "flex flex-col items-center min-h-screen bg-[url(../public/CommunityFixLandingPageImage.png)]">

      <DynamicNavbar />

      <div className = "flex flex-col backdrop-blur-xs w-full min-h-screen items-center">

        <header className = "text-[#2f4F4F] text-[1.4rem] underline">
          Make a report:
        </header>

        <main className = "flex flex-row">

          <Report />

        </main>

      </div>

    </div>
  );
}
