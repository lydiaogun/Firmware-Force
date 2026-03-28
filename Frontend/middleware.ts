import { NextResponse } from 'next/server';
import type { NextRequest } from 'next/server';

export function middleware(request: NextRequest) {

  const authCookie = request.cookies.get('session'); 

  if (!authCookie) {
    return NextResponse.redirect(new URL('/signInPage', request.url));
  }

  return NextResponse.next();
}

export const config = {
  matcher: [
    '/dashboardPage/:path*', 
    '/communityPage/:path*'
  ],
};