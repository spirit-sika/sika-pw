import {onMounted, ref} from 'vue'
import {getCaptchaAPI, loginAPI, logoutAPI, registerAPI} from "@/api/LoginAPI";
import type {CaptchaVO, UserDTO, UserRegisterDTO} from "@/types/UserType";
import {useUserStore} from "@/stores/UserStore";
import {useRouter} from "vue-router";

export const useLoginHook = () => {

    const userStore = useUserStore()
    const router = useRouter()

    const loginDTO = ref<UserDTO>({})
    const registerDTO = ref<UserRegisterDTO>({
        email: "",
        nickname: "",
        password: "",
        phoneNumber: "",
        sex: 0
    })

    const captchaVO = ref<CaptchaVO>({
        captchaKey:'',
        base64:''
    })


    const getCaptcha = async () => {
        const captcha = (await getCaptchaAPI()).data
        captchaVO.value.captchaKey = captcha.captchaKey
        loginDTO.value.codeKey = captcha.captchaKey
        captchaVO.value.base64 = captcha.base64
    }

    const handleLogin = async () => {
        const loginResponse = await loginAPI(loginDTO.value)
        if (loginResponse.code === 200) {
            const {data} = loginResponse
            userStore.setUserVO(data)
            router.push('/').then(()=> {
                ElMessage({
                    message: '登录成功.',
                    type: 'success',
                })
            })
        }
        else {
            await getCaptcha()
        }

    }

    const handleLogout = async () => {
        await logoutAPI()
        userStore.reset()
        router.push('/login')
            .then(_=>{
                ElMessage({
                    type: 'success',
                    message: '退出登录成功'
                })
            })
    }

    const handleRegister = async () => {
        const registerResult = await registerAPI(registerDTO.value);
        if (registerResult.code === 200) {
            const {data} = registerResult
            userStore.setUserVO(data)
            router.push('/').then(()=> {
                ElMessage({
                    message: '登录成功.',
                    type: 'success',
                })
            })
        }
        // todo: 注册失败
        else {

        }
        registerDTO.value = {
            email: "",
            nickname: "",
            password: "",
            phoneNumber: "",
            sex: 0
        }
    }


    const resetRegister = async () => {
        registerDTO.value = {
            email: "",
            nickname: "",
            password: "",
            phoneNumber: "",
            sex: 0

        }
    }

    onMounted(() => {
        getCaptcha().then()
    })

    return {
        loginDTO,
        captchaVO,
        registerDTO,
        getCaptcha,
        handleLogin,
        handleLogout,
        handleRegister,
        resetRegister
    }
}