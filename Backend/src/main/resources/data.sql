INSERT INTO app_users (username, emailaddress, phonenumber, dateofbirth, address, userrole, passwordhash)
VALUES 
('ehardy', 'exh444@bham.ac.uk', '07111 111111', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'ADMIN', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('praheem', 'pxr399@bham.ac.uk', '07222 222222', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'COMMUNITY', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('bbains', 'bsb478@bham.ac.uk', '07333 333333', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'ADMIN', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('logundere', 'lmo442@bham.ac.uk', '07444 444444', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'COMMUNITY', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('jisokephi', 'joi438@bham.ac.uk', '07555 555555', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'ADMIN', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('gjoyce', 'gej446@bham.ac.uk', '07666 666666', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'COMMUNITY', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('ssadeeq', 'sss430@bham.ac.uk', '07777 777777', '2006-01-01', 'University of Birmingham, Birmingham, UK', 'COMMUNITY', '$2a$10$wyZX88zCE7aM7L6MWUPHFenNCV2NG/k3/SJCCcHg2foBbrAVeoigm'),
('professorC', 'profC@bham.ac.uk', '07888 888888', '1996-01-01', 'University of Birmingham, Birmingham, UK', 'COMMUNITY', '$2a$10$ZCkjrXr6HUC/MLrhAEXYO.NltnT1Kf5I84fLB0Au97KvYz9Oqg12q'),
('professorA', 'profA@bham.ac.uk', '07999 999999', '1996-01-01', 'University of Birmingham, Birmingham, UK', 'ADMIN', '$2a$10$1LMS4t3PEhlEC5RaytlX4.FMumzCO3lkQx/8DYAnqHPO/t.x1ZHX.');

INSERT INTO reports (datereported, category, issue, priority, reportlocation, reportstatus, userid, critical, votes)
VALUES 
('2025-12-15 08:30:00','ROAD', 'POTHOLE', 5, '52.443643, -1.936192', 'IN_PROGRESS', 2, 0, 2),
('2025-12-24 18:00:00','ROAD', 'OBSTRUCTION', 7, '52.442154, -1.937773', 'IN_PROGRESS', 8, 1, 0),
('2025-12-31 23:59:59','ROAD', 'CRASH', 9, '52.445216, -1.934202', 'IN_PROGRESS', 4, 1, 0),
('2026-01-05 09:15:22','PAVEMENT', 'CRACKS', 2, '52.443963, -1.935905', 'NOT_STARTED', 7, 0, 1),
('2026-01-14 14:45:10','PAVEMENT', 'LOOSE_TILE', 4, '52.445711, -1.933542', 'NOT_STARTED', 8, 0, 2),
('2026-01-28 03:12:45','PAVEMENT', 'OBSTRUCTION', 5, '52.447057, -1.933381', 'IN_PROGRESS', 4, 0, 2),
('2026-02-02 11:00:00','WASTE', 'OVERFLOWING_BIN', 6, '52.447109, -1.933610', 'NOT_STARTED', 7, 0, 2),
('2026-02-14 20:30:00','WASTE', 'FLY_TIPPING', 7, '52.443207, -1.936891', 'IN_PROGRESS', 2, 1, 0),
('2026-02-27 17:05:12','WASTE', 'SEWAGE_SPILL', 9, '52.442292, -1.937440', 'NOT_STARTED', 4, 1, 0),
('2026-03-03 12:00:00','SAFETY', 'ILLEGAL_ACTIVITY', 8, '52.444368, -1.936145', 'IN_PROGRESS', 2, 1, 0),
('2026-01-10 07:45:30','SAFETY', 'LARGE_GROUP_GATHERING', 2, '52.444597, -1.934819', 'NOT_STARTED', 4, 0, 4),
('2026-03-12 22:18:05','SAFETY', 'ANTISOCIAL_BEHAVIOUR', 7, '52.446187, -1.936250', 'INVESTIGATING', 7, 0, 4);

INSERT INTO notifications (reportid, userid, notificationtext)
VALUES
(2, 8, 'Report 2 status updated to: IN_PROGRESS'),
(5, 8, 'Report 5 status updated to: FIXED');

INSERT INTO votes (userid, reportid, votetype)
VALUES
(2, 1, 'UP'),
(4, 1, 'UP'),
(6, 1, 'UP'),
(7, 1, 'DOWN'),
(2, 4, 'UP'),
(2, 5, 'UP'),
(4, 5, 'UP'),
(6, 6, 'UP'),
(7, 6, 'UP'),
(2, 7, 'UP'),
(4, 7, 'UP'),
(2, 11, 'UP'),
(4, 11, 'UP'),
(6, 11, 'UP'),
(7, 11, 'UP'),
(2, 12, 'UP'),
(4, 12, 'UP'),
(6, 12, 'UP'),
(7, 12, 'UP');