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
      <el-form-item label="Контакты" prop="contacts">
        <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 5}"
          placeholder="Контакты"
          v-model="form.contacts">
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
  import { getUsers } from '../api/user'

  export default {
    name: 'CreateResumeForm',
    data () {
      return {
        dialogVisible: false,
        form: {
          resumeTitle: '',
          resumeUserId: '',
          workExperience: '',
          contacts: ''
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
          ],
          contacts: [
            { required: true, message: 'Заполните контакты', trigger: 'blur' }
          ]
        },
        applicants: []
      }
    },
    methods: {
      open () {
        this.loadApplicants()
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
              workExperience: this.form.workExperience,
              contacts: this.form.contacts
            })
              .then(() => {
                this.dialogVisible = false
                this.clearForm()
              })
          } else {
            return false
          }
        })
      },
      loadApplicants () {
        getUsers('applicant')
          .then(response => {
            this.applicants = response.data
          })
      }
    }
  }
</script>

<style scoped>

</style>
