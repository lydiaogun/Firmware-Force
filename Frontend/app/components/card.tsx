interface CardProps {
  reportId: string;
  dateReported: string;
  category: string;
  issue: string;
  location: string;
  status: string;
  voteCount: number;
  onUpVote: () => void;
  onDownVote: () => void;
}

export default function Card({reportId, dateReported, category, issue, location, status, voteCount, onUpVote, onDownVote}: CardProps) {
    return (
        <div className = "card h-[500px] w-[260px] text-wrap rounded">

            <h2 className = "underline"> Report: </h2>
            <p> {reportId} </p>
            <p className = "underline"> Date of report: </p>
            <p> {dateReported} </p>
            <p className = "underline"> Report category: </p>
            <p> {category} </p>
            <p className = "underline"> Report issue: </p>
            <p> {issue} </p>
            <p className = "underline"> Report location: </p>
            <p> {location} </p>
            <p className = "underline"> Report status: </p>
            <p> {status} </p>
            <p className = "underline"> Votes: </p>
            <p> {voteCount} </p>

            <div className = "flex flex-col">
                <button onClick = {onUpVote} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> Up Vote </button>
                <button onClick = {onDownVote} className = "flex flex-col bg-[#2F4F4F] text-white items-center gap-2 p-2 border rounded hover:bg-[#263D3D] transition-colors"> Down Vote </button>
            </div>
        </div>
    );
}
