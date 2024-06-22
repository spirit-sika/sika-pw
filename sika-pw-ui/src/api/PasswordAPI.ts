import request from "@/utils/request";
import type {PageInfo} from "@/types/CommonType";
import type {PasswordDTO, PasswordVO} from "@/types/PasswordType";

export interface SearchPasswordDTO {
    domainName: string,
    account: string,
    condition: string,
    pageNum?: number,
    pageSize?: number
}
export const getPasswordAPI = (searchDTO:SearchPasswordDTO) => {
    return request<PageInfo<PasswordVO>>({
        url: 'user/pw/list-own',
        method: 'GET',
        params: searchDTO
    })
}

export const addPasswordAPI = (passwordDTO: PasswordDTO) => {
    return request<string>({
        url: 'user/pw',
        method: 'POST',
        data: passwordDTO
    })
}

export const updatePasswordAPI = (passwordDTO: PasswordDTO) => {
    return request<string>({
        url: 'user/pw',
        method: 'PUT',
        data: passwordDTO
    })
}

export const deletePasswordAPI = (id: number) => {
    return request<string>({
        url: 'user/pw',
        method: 'DELETE',
        data:id
    })
}