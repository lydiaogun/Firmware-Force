/*
Hero with bold headline, short subheadline. 
And a 3d image of the globe as the image to the right of the
headline and subheadline to capture the attention.  */

export default function HomeHeroHeader() {
    return (
        <div className="flex flex-col md:flex-row h-[420px] items-center bg-[url(../public/CommunityFixLandingPageImage.png)] justify-between p-20">
            
            <div className="md:w-1/2 mb-8 md:mb-0 bg-white rounded border-2 border-[#2F4F4F] p-4">
                <h1 className="text-4xl md:text-5xl text-[#2F4F4F] font-bold mb-4 underline">Report issues, build better streets</h1>
                <p className="text-lg md:text-xl text-[#2F4F4F]">CommunityFix connects you directly with your council. Spot a problem, report it in seconds, and watch your neighbourhood transform through real action and community support.</p>
            </div>
            
        </div>
    );
}