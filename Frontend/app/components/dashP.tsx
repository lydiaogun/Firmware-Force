"use client";
import DashCardP from "./dashCardP";
import { useState, useEffect } from 'react';
import { apiUrl } from '@/lib/api';

interface Report {
    reportId: string;
    dateReported: string;
    category: string;
    issue: string;
    priority: string;
    location: string;
    status: string;
    votes: number;
}

export default function DashP() {

    const [reports, setReports] = useState<Report[]>([]);

    const loadReports = (() => {
        fetch(apiUrl('/api/reports/priority')).then((response) => response.json()).then((data) => setReports(data))
    });

    useEffect(() => {
        loadReports()
    }, [])

    const handleInvestigating = ((reportId: string) => {
        fetch(apiUrl(`/api/reports/INVESTIGATING/${reportId}/status`), {method: 'PUT'}).then((response) => {
            if (!response.ok){
                throw new Error ('Error updating status')
            }
            else
            {
                loadReports();
            }
        })
    });

    const handleInProgress = ((reportId: string) => {
        fetch(apiUrl(`/api/reports/IN_PROGRESS/${reportId}/status`), {method: 'PUT'}).then((response) => {
            if (!response.ok){
                throw new Error ('Error updating status')
            }
            else
            {
                loadReports();
            }
        })
    });

    const handleFixed = ((reportId: string) => {
        fetch(apiUrl(`/api/reports/FIXED/${reportId}/status`), {method: 'PUT'}).then((response) => {
            if (!response.ok){
                throw new Error ('Error updating status')
            }
            else
            {
                loadReports();
            }
        })
    });

    const handleNotStarted = ((reportId: string) => {
        fetch(apiUrl(`/api/reports/NOT_STARTED/${reportId}/status`), {method: 'PUT'}).then((response) => {
            if (!response.ok){
                throw new Error ('Error updating status')
            }
            else
            {
                loadReports();
            }
        })
    });

    return (
        
        <div className = "flex flex-col items-center h-[800px] w-[820px] bg-white border-2 border-[#2F4F4F] overflow-y-auto rounded">

            <div className = "grid grid-cols-3 p-2 h-[742px] w-[810px]">

                {reports.map((report: Report) => (
                    <DashCardP key = {report.reportId} reportId = {report.reportId} dateReported = {report.dateReported} category = {report.category} issue = {report.issue} priority = {report.priority} location = {report.location} status = {report.status} voteCount = {report.votes} onInvestigating = {() => handleInvestigating(report.reportId)} onInProgress = {() => handleInProgress(report.reportId)} onFixed = {() => handleFixed(report.reportId)} onNotStarted = {() => handleNotStarted(report.reportId)} />
                ))}

            </div>  

        </div>
    )
};
