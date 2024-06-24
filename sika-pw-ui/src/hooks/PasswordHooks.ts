import {onMounted, ref} from 'vue'
import type {PageInfo} from '@/types/CommonType';
import {
    addPasswordAPI,
    deletePasswordAPI,
    getPasswordAPI,
    type SearchPasswordDTO,
    updatePasswordAPI
} from "@/api/PasswordAPI";
import type {PasswordDTO, PasswordVO} from "@/types/PasswordType";
import type {FormInstance} from "element-plus";
import {SikaModal} from "@/components/modal";

export const usePasswordHooks = () => {
    /* 搜索表单, 根据域名 账号搜索 */
    const searchForm = ref<SearchPasswordDTO>({
        domainName: "",
        account: "",
        condition: "",
        pageNum: 1,
        pageSize: 10
    })

    /* 信息列表 */
    const passwordList = ref<Array<PasswordVO>>([])
    const pageInfo = ref<PageInfo<PasswordVO>>()

    const title = ref<"添加密码" | "修改密码">("添加密码")
    const open = ref<boolean>(false)
    const passwordDTO = ref<PasswordDTO>({account: "", domainName: "", password: ""})


    const getPassword = async () => {
        pageInfo.value = (await getPasswordAPI(searchForm.value)).data
        passwordList.value = pageInfo.value.list
    }
    const handleSizeChange = (size: number) => {
        searchForm.value.pageSize = size;
        getPassword()
    }
    const handleCurrentChange = (num: number) => {
        searchForm.value.pageNum = num;
        getPassword()
    }
    const resetSearchFormAndGetPassword = () => {
        searchForm.value = {
            account: "",
            condition: "",
            domainName: ""
        }
        getPassword()
    }
    const resetDialogForm = (formEl: FormInstance | undefined) => {
        passwordDTO.value = {pwId: undefined, account: "", domainName: "", password: ""}
        if (!formEl) return
        formEl.resetFields()
    }
    const submitForm = async () => {
        let result;
        // 存在id, 修改操作
        if (passwordDTO.value.pwId) {
            result = await updatePasswordAPI(passwordDTO.value)
        }
        // 没有id, 新增操作
        else {
            result = await addPasswordAPI(passwordDTO.value)
        }
        if (result.code === 200) {
            ElMessage({
                type: "success",
                message: result.data
            })
        }
        open.value = false;
        getPassword()
    }
    const handleUpdate = (row: PasswordVO) => {
        title.value = "修改密码";
        open.value = true;
        passwordDTO.value.pwId = row.pwId
        passwordDTO.value.domainName = row.domainName
        passwordDTO.value.account = row.account
        passwordDTO.value.password = row.password
    }
    const handleDelete = async (id: number, account: string) => {
        const data = await SikaModal.confirm('是否确认删除账户为"' + account + '"的数据项？')
        if (data === 'confirm') {
            console.log(`id is ${id}`)
            deletePasswordAPI(id)
                .then(async res => {
                    await getPassword()
                    ElMessage({
                        type: "success",
                        message: res.data
                    })
                })
        }
    }
    const copy = (content:string|undefined, tips: string|undefined) => {
        if(content === undefined || content === null) {
            return
        }
        if (navigator.clipboard && window.isSecureContext) {
            navigator.clipboard.writeText(content)
                .then(()=> {
                    if(tips !== undefined && tips !== null) {
                        ElMessage({
                            type: "success",
                            message: tips
                        })
                    }
                    else {
                        ElMessage({
                            type: "success",
                            message: '复制成功'
                        })
                    }
                })
        }
        // 非安全上下文(https协议)无法使用clipboard, 使用dom节点复制
        else {
            const inputElement = document.createElement('input');
            inputElement.value = content
            inputElement.style.position = 'absolute'
            inputElement.style.left = '-999999px';
            document.body.append(inputElement);
            inputElement.select();

            try {
                document.execCommand('copy');
                ElMessage({
                    type: "success",
                    message : '复制成功'
                })
            } catch (e) {
                console.log(e)
                ElMessage({type: "error", message: '复制失败, 请手动复制或更换浏览器重试'})
            } finally {
                inputElement.remove()
            }
        }

    }

    onMounted(() => getPassword())

    return {
        searchForm,
        passwordList,
        pageInfo,
        title,
        open,
        passwordDTO,
        copy,
        getPassword,
        resetSearchFormAndGetPassword,
        resetDialogForm,
        submitForm,
        handleSizeChange,
        handleCurrentChange,
        handleUpdate,
        handleDelete
    }
}