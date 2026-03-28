"use client";

import DynamicNavBar from '../components/dynamicNavBar';
import SignIn from '../components/signIn';

export default function SignInPage() {

  return (
      <>
          <header>

              <DynamicNavBar/>

          </header>

        <main className="flex flex-col space-y-10 bg-[url(../public/CommunityFixLandingPageImage.png)] items-center min-h-screen">

            <div className = "backdrop-blur-xs w-full min-h-screen">

                <section aria-label="Sign in area" className="flex flex-col items-center py-38">

                    <SignIn/>

                </section>

            </div>

        </main>
    </>
  );
}