/* 
Why us section

Another set of cards similar to the homeFeaturesListSection, but with more bigger cards
cards to be vertically flexed providing information of the timeline of a typical user's journey in the app
it will implement Relume's card design of card layout with flex-col, grid-cols-2, border-border-primary, and padding
each card will have an image on the left and text on the right, with a title and description of each step in the user's journey.
I.e.
1. first of all, report an issue, 
2. next you can see the Community voting mechanism on not just your issue card, but on all other issue reports published from other people too, 
3. and then you can finally see real time status updates on your reported issue.

The aim of the cards is to show the user why they should report issues on this website.
*/

export default function HomeFeaturesListSection() {

    return (
        <div className="flex flex-col items-center justify-center w-full p-10 bg-[#1560BD] dark:bg-gray-800">
            <h2 className="text-3xl font-bold mb-8 text-gray-800 dark:text-gray-200">Your Journey with CommunityFix</h2>
            <div className="grid grid-cols-1 md:grid-cols-1 gap-8 w-full max-w-4xl">
                {/* Card 1 */}
                <div className="flex flex-col md:flex-row items-center bg-white dark:bg-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 rounded-lg shadow-md p-6 border border-gray-300 dark:border-gray-600">
                    <img src="/phone-camera.png" alt="Report Issue" className="w-full md:w-1/3 rounded-lg mb-4 md:mb-0" />
                    <div className="md:ml-6">
                        <h3 className="text-xl font-semibold mb-2 text-gray-800 dark:text-gray-200">Report</h3>
                        <h4 className="text-lg font-medium mb-2 text-gray-700 dark:text-gray-300">Spot it, send it</h4>
                        <p className="text-gray-600 dark:text-gray-300">You see a broken pavement. Open CommunityFix, choose the category and issue, add the location. Done in under a minute.</p>
                    </div>
                </div>
                {/* Card 2 */}
                <div className="flex flex-col md:flex-row items-center bg-white dark:bg-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 rounded-lg shadow-md p-6 border border-gray-300 dark:border-gray-600">
                    <img src="/teamwork.png" alt="Community Voting" className="w-full md:w-1/3 rounded-lg mb-4 md:mb-0" />
                    <div className="md:ml-6">
                        <h3 className="text-xl font-semibold mb-2 text-gray-800 dark:text-gray-200">Vote</h3>
                        <h4 className="text-lg font-medium mb-2 text-gray-700 dark:text-gray-300">Your neighbours shape what gets fixed</h4>
                        <p className="text-gray-600 dark:text-gray-300">Others see your reports and votes. Issues with real community backing rise to the top. Council teams see what matters most. Your voice counts alongside everyone else&apos;s.</p>
                    </div>
                </div>
                {/* Card 3 */}
                <div className="flex flex-col md:flex-row items-center bg-white dark:bg-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 rounded-lg shadow-md p-6 border border-gray-300 dark:border-gray-600">
                    <img src="/worker.png" alt="Status Updates" className="w-full md:w-1/3 rounded-lg mb-4 md:mb-0" />
                    <div className="md:ml-6">
                        <h3 className="text-xl font-semibold mb-2 text-gray-800 dark:text-gray-200">Track</h3>
                        <h4 className="text-lg font-medium mb-2 text-gray-700 dark:text-gray-300">Know exactly what&apos;s happening now</h4>
                        <p className="text-gray-600 dark:text-gray-300">Council assigns a crew. Work starts. You get updates as things progress. No guessing, no silence, just real transparency from start to finish.</p>
                    </div>
                </div>
            </div>
        </div>
        
    )
}
