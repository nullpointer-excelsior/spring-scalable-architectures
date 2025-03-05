export function getRandomElements<T>(array: T[], limit: number): T[] {
    if (limit === 0) return [];
    const shuffled = array.sort(() => Math.random() - 0.5);
    return shuffled.slice(0, Math.min(limit, array.length));
}
