interface CardProps {
  reportId: string;
  dateReported: string;
  category: string;
  issue: string;
  priority: string;
  location: string;
  status: string;
  voteCount: number;
  onInvestigating: () => void;
  onInProgress: () => void;
  onFixed: () => void;
  onNotStarted: () => void;
}

export default function DashCardV({reportId, dateReported, category, issue, priority, location, status, voteCount, onInvestigating, onInProgress, onFixed, onNotStarted}: CardProps) {
    
    return (

        <div className = "card h-[620px] w-[260px] text-wrap rounded">

            <h2 className = "underline"> Report: </h2>
            <p> {reportId} </p>
            <p className = "underline"> Date of report: </p>
            <p> {dateReported} </p>
            <p className = "underline"> Report category: </p>
            <p> {category} </p>
            <p className = "underline"> Report issue: </p>
            <p> {issue} </p>
            <p className = "underline"> Report priority: </p>
            <p> {priority} </p>
            <p className = "underline"> Report location: </p>
            <p> {location} </p>
            <p className = "underline"> Report status: </p>
            <p> {status} </p>
            <p className = "underline"> Votes: </p>
            <p className = "text-[#3242a8]"> {voteCount} </p>

            <div className = "flex flex-col g-2 p-2">

                <button onClick = {onInvestigating} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> Investigating </button>
                <button onClick = {onInProgress} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> In progress </button>
                <button onClick = {onFixed} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> Fixed </button>
                <button onClick = {onNotStarted} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> Not started </button>

            </div>

        </div>
    );
}