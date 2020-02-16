<template>
  <el-dialog
    title="Создание резюме"
    :visible.sync="dialogVisible"
    width="30%"
    :before-close="handleClose">
    <el-form ref="createResumeForm" :model="form" :rules="rules">
      <el-form-item label="Заголовок" prop="resumeTitle">
        <el-input v-model="form.resumeTitle"/>
      </el-form-item>
      <el-form-item label="Пользователь" prop="resumeUserId">
        <el-select v-model="form.resumeUserId" placeholder="Select">
          <el-option
            v-for="applicant in applicants"
            :key="applicant.id"
            :label="applicant.name"
            :value="applicant.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="Опыт работы" prop="workExperience">
        <el-input
          type="textarea"
          :autosize="{ minRows: 3, maxRows: 20}"
          placeholder="Ваш опыт работы"
          v-model="form.workExperience">
        </el-input>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="onCreateUserClick">Создать</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import { createResume } from './../api/resume'

  export default {
    name: 'CreateResumeForm',
    data () {
      return {
        dialogVisible: false,
        form: {
          resumeTitle: '',
          resumeUserId: '',
          workExperience: ''
        },
        rules: {
          resumeTitle: [
            { required: true, message: 'Заполните заголовок', trigger: 'blur' }
          ],
          resumeUserId: [
            { required: true, message: 'Выберите пользователя', trigger: 'change' }
          ],
          workExperience: [
            { required: true, message: 'Заполните опыт работы', trigger: 'blur' }
          ]
        },
        applicants: [
          {
            id: 1,
            name: 'User name'
          },
          {
            id: 2,
            name: 'User name 2'
          },
          {
            id: 3,
            name: 'User name 3'
          },
          {
            id: 4,
            name: 'User name 4'
          },
        ]
      }
    },
    methods: {
      open () {
        this.dialogVisible = true
      },
      clearForm () {
        this.$refs['createResumeForm'].resetFields()
      },
      handleClose (done) {
        this.clearForm()
        done()
      },
      onCreateUserClick () {
        this.$refs['createResumeForm'].validate((valid) => {
          if (valid) {
            createResume({
              title: this.form.resumeTitle,
              userId: this.form.resumeUserId,
              workExperience: this.form.workExperience
            })
              .then(() => {
                this.clear()
                this.dialogVisible = false
              })
              .finally(() => this.clearForm())
          } else {
            return false
          }
        })
      }
    }
  }
</script>

<style scoped>

</style>
