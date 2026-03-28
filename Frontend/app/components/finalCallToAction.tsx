/*
Final Encouragement for users to Quick Report (with a Quick Report! button) and a learn more button next to it. 
Prominent call-to-action button which will be a 'Quick Report' button.
*/

import { useRouter } from "next/navigation";

export default function FinalCallToAction() {
    const router = useRouter();

    return (
        <div className="flex flex-col items-center justify-center w-full p-20 bg-white dark:bg-gray-800">
            <h2 className="text-3xl font-bold mb-8 text-gray-800 dark:text-gray-200">Your neighbourhood needs you</h2>
            <p className="text-lg text-gray-600 dark:text-gray-300 mb-6">The fastest way to fix what&apos;s broken is to speak up. Report now and be part of the solution.</p>
            <div className="flex space-x-4">
                <button onClick={() => router.push('/reportPage')} className="bg-[#2F4F4F] text-white px-6 py-3 rounded hover:bg-[#263D3D] transition-colors"> Quick Report </button>
                <button onClick={() => router.push('/aboutUs')} className="bg-[#2F4F4F] text-white px-6 py-3 rounded hover:bg-[#263D3D] transition-colors"> Learn More </button>
            </div>
        </div>
    )
}
