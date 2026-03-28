"use client";

import DynamicNavBar from '../components/dynamicNavBar';

export default function AboutUsPage() {

    return (
        <div className = "flex flex-col min-h-screen bg-[url(../public/CommunityFixLandingPageImage.png)] w-full items-center bg-[#2F4F4F]">

            <DynamicNavBar />

            <div className = "backdrop-blur-xs min-h-screen w-full">

                <main id="main-content" className="flex flex-col items-center">

                    <header className = "text-[#2F4F4F] items-center text-[2.25rem] underline">
                        About us:
                    </header>
                    
                    <div className = "p-20 flex flex-col items-center">

                        <div className="flex flex-col items-center border-2 border-[#2F4F4F] p-20 bg-white rounded">

                            <p className = "text-[#2F4F4F] text-[1.5rem] items-aligned-left text-center w-[24em]"> 
                                We wanted to create a website that allows councils to gain better understanding into the various issues affecting the community. Our website gives people the opportunity to make their voices heard, and make positive change to our environment. By making reports to our website, you bring attention to issues affecting everyone, and help us make our community a better place to be. <br></br><br></br> Sincerely, FirmwareForce. 
                            </p>

                        </div>  

                    </div>

                </main>

            </div>

        </div>
    )
}
