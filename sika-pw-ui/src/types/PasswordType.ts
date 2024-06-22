export interface PasswordVO {
    pwId: number
    domainName: string
    account: string
    password: string
    createBy: string
    createTime: string
    updateBy: string
    updateTime: string
}

export interface PasswordDTO {
    pwId?: number,
    domainName: string;
    account: string;
    password: string;
}