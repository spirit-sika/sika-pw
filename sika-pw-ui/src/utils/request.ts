import type {AxiosRequestConfig, InternalAxiosRequestConfig} from 'axios'
import axios from 'axios'

import router from "@/router";
import {useUserStore} from "@/stores/UserStore";

import type {Result} from "@/types/CommonType";


interface SikaRequestConfig extends AxiosRequestConfig {
    notAuth?: boolean
}



const axiosInstant = axios.create({
    baseURL: import.meta.env.VITE_APP_BASE_API,
    timeout: 5000
})
axiosInstant.defaults.headers['Content-Type'] = 'application/json;charset=utf-8';


axiosInstant.interceptors.request.use((config: SikaRequestConfig) => {
    const userStore = useUserStore();
    // 添加token到请求头中
    if (config.notAuth === undefined || !config.notAuth) {
        const token = userStore.getUserToken()
        // 用户未登录, 没有token信息或者已清楚本地存储, 需要重新登录
        if(typeof token === undefined || token === null || token.length === 0) {
            ElMessageBox({
                title: '身份失效',
                message: '登录信息已过期, 是否重新登录',
                showCancelButton: true,
                confirmButtonText: '重新登录',
                cancelButtonText: '留在页面',
            }).then(action => {
                if (action === 'confirm') {
                    // 重新登录
                    console.log('页面跳转')
                    router.push('/login').then()
                }
            })
        }
        config.headers!['sika'] = `Bearer ${token}`
    }

    return config as InternalAxiosRequestConfig
})


const request = async <T>(config:SikaRequestConfig):Promise<Result<T>> => {
    const response = await axiosInstant.request<Result<T>>(config);

    // 网络层面错误
    if (response.status !== 200) {
        ElMessage({
            type: 'error',
            message: response.statusText,
        })
        return {
            message: response.statusText,
            code: response.status,
            data: null as T
        }
    }

    // 服务器层面错误
    const {data} = response
    if (data.code !== 200) {
        ElMessage({
            type: 'error',
            message: data.message,
        })
    }

    return response.data
}

export default request;
