import {defineStore} from 'pinia'
import {ref} from "vue";
import type {UserVO} from "@/types/UserType";

export const useUserStore = defineStore('user', () => {
    const userVO = ref<UserVO>({
        userId: 0,
        nickname: "",
        email: "",
        phoneNumber: "",
        sex: "未知",
        token: ""
    })

    /**
     * 通过用户登录的响应信息设置state的loginVO
     * @param loginResult 用户登录的响应信息对象LoginVO
     */
    const setUserVO = (loginResult: UserVO) => {
        userVO.value.userId = loginResult.userId
        userVO.value.nickname = loginResult.nickname
        userVO.value.email = loginResult.email
        userVO.value.phoneNumber = loginResult.phoneNumber
        userVO.value.sex = loginResult.sex
        userVO.value.token = loginResult.token
    }

    /**
     * 获取整个用户的vo对象
     */
    const getUserVO = () => {
        return userVO.value
    }

    /**
     * 获取用户token
     */
    const getUserToken = () => {
        return userVO.value.token
    }

    /**
     * 获取用户昵称
     */
    const getUserNickname = () => {
        return userVO.value.nickname
    }

    /**
     * 获取用户性别
     */
    const getUserSex = () => {
        return userVO.value.sex
    }

    /**
     * 检查是否存在登录状态, 未登录时返回false
     */
    const notLogin = () => {
        return userVO.value.token === undefined
            || userVO.value.token === ''
            || userVO.value.token.length === 0;
    }

    /**
     * 重置整个state
     */
    const reset = () => {
        userVO.value.userId = 0
        userVO.value.nickname = ''
        userVO.value.email = ''
        userVO.value.phoneNumber = ''
        userVO.value.sex = '未知'
        userVO.value.token = ''
    }

    return {
        userVO,
        setUserVO,
        getUserVO,
        getUserToken,
        getUserNickname,
        getUserSex,
        notLogin,
        reset
    }
}, {
    persist: true
})