"use client";
import DynamicNavBar from "./components/dynamicNavBar";
import FinalCallToAction from "./components/finalCallToAction";
import HomeFeaturesListSection from "./components/homeFeaturesListSection";
import HomeHeroHeader from "./components/homeHeroSection";
import HomeJourneySection from "./components/homeJourneySection";
import LegalBodySection from "./components/legalBodySection";
// This is the main landing page for our CommunityFix, the entry
/*
Features thats going to be tackled within this page:
- Quick Report Button and category icons preview section (F-9, F-11)
*/

export default function Home() {
    return (
        <>
            <header>
                <DynamicNavBar />
            </header>

            <main id = "main-content">
                <HomeHeroHeader />

                <section>
                    <HomeFeaturesListSection />
                </section>

                <section>
                    <HomeJourneySection />
                </section>

                <section>
                    <FinalCallToAction />
                </section>
            </main>

            <footer>
                <LegalBodySection />
            </footer>
        </>
    )
}