const fallbackApiBaseUrl = "http://localhost:8080";

function trimTrailingSlash(value: string) {
  return value.endsWith("/") ? value.slice(0, -1) : value;
}

export function getApiBaseUrl() {
  return trimTrailingSlash(
    process.env.NEXT_PUBLIC_API_BASE_URL || fallbackApiBaseUrl
  );
}

export function apiUrl(path: string) {
  const normalizedPath = path.startsWith("/") ? path : `/${path}`;
  return `${getApiBaseUrl()}${normalizedPath}`;
}
