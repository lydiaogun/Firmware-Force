'use server'

import { cookies } from 'next/headers';

export async function createSession(token: string, userRole: string, userId: string) {
  const cookieStore = await cookies();
  
  if (userRole == "ADMIN")
  {
    cookieStore.set('session', token, {
      httpOnly: true,
      secure: process.env.NODE_ENV === 'production',
      maxAge: 60 * 60 * 24, 
      path: '/',
    });

    cookieStore.set('userRole', userRole, {
      httpOnly: true,
      path: '/',
    });

    cookieStore.set('userId', userId, {
      httpOnly: true,
      path: '/',
    });
  }
  else
  {
    cookieStore.set('session', token, {
      httpOnly: true,
      secure: process.env.NODE_ENV === 'production',
      maxAge: 60 * 60 * 24, 
      path: '/communityPage',
    });

    cookieStore.set('userRole', userRole, {
      httpOnly: true,
      path: '/communityPage',
    });

    cookieStore.set('userId', userId, {
      httpOnly: true,
      path: '/communityPage',
    });
  }
}

export async function getUserId() {
  const cookieStore = await cookies();
  const userId = cookieStore.get('userId')?.value;
  
  return userId ?? null;
}