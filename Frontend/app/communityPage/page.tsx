"use client";

import { CookiesProvider } from 'react-cookie';
import DynamicNavBar from '../components/NavBarInC';
import CommReport from '../components/commReport';
import { useEffect, useState } from 'react';
import { getUserId } from '../actions';
import Card from '../components/card';
import { apiUrl } from '@/lib/api';

interface Notification {
    reportId: number;
    userId: number;
    notificationText: string;
    notificationId: string;
}

interface Report {
    reportId: string;
    dateReported: string;
    category: string;
    issue: string;
    location: string;
    status: string;
    votes: number;
}

export default function CommunityPage() {
  
  const [notifications, setNotifications] = useState<Notification[]>([]);

  const [userId, setUserId] = useState("");

  useEffect(() => {
    const loadNotifications = async () => {
      try {
        const id = await getUserId();

        if (id) {
          const response = await fetch(apiUrl(`/api/notifications/${id}`));

          setUserId(id)

          if (!response.ok) throw new Error("Cannot connect to server");
          
          const data = await response.json();
          setNotifications(data);
        }
      } catch (err) {
        console.error("Fetch error:", err);
      }
    };

    loadNotifications();
    loadReports();
  }, []);

  const handleClose = ( async (notificationId: string) => {
    try {
      const response = await fetch(apiUrl(`/api/notifications/delete/${notificationId}`), {
        method: 'DELETE', 
      });

      if (response.ok) {
        setNotifications((prev) => prev.filter(n => n.notificationId !== notificationId));
      } else {
        console.error("Failed to delete from database");
      }
    } catch (error) {
      console.error("Network error:", error);
    }
  });

      const [reports, setReports] = useState<Report[]>([]);
  
      const loadReports = (() => {
          fetch(apiUrl('/api/reports/votes')).then((response) => response.json()).then((data) => setReports(data))
      });
  
      const handleUpVote = ((reportId: string) => {
          fetch(apiUrl(`/api/reports/upvote/${reportId}/${userId}`), {method: 'PUT'}).then((response) => {
              if (!response.ok){
                  throw new Error ('Error voting')
              }
              else
              {
                  loadReports();
              }
          })
      });
  
      const handleDownVote = ((reportId: string) => {
          fetch(apiUrl(`/api/reports/downvote/${reportId}/${userId}`), {method: 'PUT'}).then((response) => {
              if (!response.ok){
                  throw new Error ('Error voting')
              }
              else
              {
                  loadReports();
              }
          })
      });

  return (

    <CookiesProvider>

    <div className = "flex flex-col min-h-screen items-center bg-[url(../public/CommunityFixLandingPageImage.png)] font-sans dark:bg-black">

        <DynamicNavBar />

          <div className="fixed top-25 left-5 z-50 flex flex-col gap-4">

              {notifications.map((notification) => (

                <div key={notification.notificationId} className="w-80 rounded-lg bg-white p-4 shadow-xl border-l-4 border-red-600 animate-in fade-in slide-in-from-right-4">

                  <div className="flex items-center justify-between pb-2 border-b border-gray-100">

                    <span className="font-bold text-[#2F4F4F]">

                      Notification:

                    </span>

                    <button onClick={() => handleClose(notification.notificationId)} className="text-[#2F4F4F] text-xl leading-none border-2 border-[#2F4F4F]">&times; </button>

                  </div>

                  <div className="pt-2 text-sm text-[#2F4F4F]">

                    {notification.notificationText}

                  </div>

                </div>

              ))}

          </div>

          <div className = "flex flex-row justify-center backdrop-blur-xs min-h-screen w-full">

            <main className = "flex flex-row items-center py-10 space-x-20 ">

              <div className = "flex flex-col items-center">

                <header className = "text-[#2F4F4F] text-[22px] underline">

                  Vote here:

                </header>

                <div className="flex flex-col items-center border-2 border-[#2F4F4F] h-[800px] w-[556px] space-y-4 bg-white overflow-y-auto rounded">

                  <div className = "grid grid-cols-2 gap-2 p-2 h-[742px] w-[546]">
                    {reports.map((report: Report) => (
                      <Card key = {report.reportId} reportId = {report.reportId} dateReported = {report.dateReported} category = {report.category} issue = {report.issue} location = {report.location} status = {report.status} voteCount = {report.votes} onUpVote = {() => handleUpVote(report.reportId)} onDownVote = {() => handleDownVote(report.reportId)} />
                    ))}
                  </div>

                </div>

              </div>

              <CommReport />

            </main>

          </div>

    </div>

    </CookiesProvider>

  );
}
