export interface Result<T> {
    code: number
    message: string
    data: T
}

export interface PageInfo<T> {
    pageNum: number,
    pageSize: number,
    // 当前页的数量
    size: number,
    pages: number,
    isFirstPage: boolean,
    isLastPage: boolean,
    total: number,
    list: Array<T>
}