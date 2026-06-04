export const formatTime = (dateString) => {
    const now = new Date();
    const time = new Date(dateString);

    const diffMs = now - time;

    const diffMinutes = Math.floor(diffMs / (1000 * 60));
    const diffHours = Math.floor(diffMs / (1000 * 60 * 60));
    const diffDays = Math.floor(diffMs / (1000 * 60 * 60 * 24));
    const diffMonths = Math.floor(diffDays / 30);
    const diffYears = Math.floor(diffDays / 365);

    if (diffMinutes < 1) {
        return `Just now`;
    }


    if (diffHours < 1) {
        return `${diffMinutes} minute${diffMinutes !== 1 ? "s" : ""} ago`;
    }

    if (diffDays < 1) {
        return `${diffHours} hour${diffHours !== 1 ? "s" : ""} ago`;
    }

    if (diffDays < 30) {
        return `${diffDays} day${diffDays !== 1 ? "s" : ""} ago`;
    }

    if (diffDays < 365) {
        return `${diffMonths} month${diffMonths !== 1 ? "s" : ""} ago`;
    }

    return `${diffYears} year${diffYears !== 1 ? "s" : ""} ago`;
};