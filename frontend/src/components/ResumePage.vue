<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <!--<el-button-group>-->
        <el-button
          @click="onCreateUserButtonClick"
          type="success">
          Создать пользователя
        </el-button>
        <el-button
          @click="onCreateResumeButtonClick"
          type="success">
          Создать резюме
        </el-button>
        <el-button
          type="success">
          Создать компанию
        </el-button>
        <el-button
          type="success">
          Создать вакансию
        </el-button>
        <!--</el-button-group>-->
      </el-col>
    </el-row>
    <el-row :class="'content'">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="Вакансии" name="vacancies">
          <el-table
            :data="vacancies"
            style="width: 100%">
            <el-table-column
              label="Название"
              prop="title">
            </el-table-column>
            <el-table-column
              label="Date"
              prop="date">
            </el-table-column>
            <el-table-column width="300px"
                             align="right">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">Подробнее
                </el-button>
                <el-button
                  size="mini"
                  @click="handleEdit(scope.$index, scope.row)">Добавить отклик
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="Резюме" name="resume"></el-tab-pane>
      </el-tabs>
    </el-row>
    <create-user-form ref="createUserForm"/>
    <create-resume-form ref="createResumeForm"/>
  </div>
</template>

<script>
  import CreateUserForm from './CreateUserForm'
  import CreateResumeForm from './CreateResumeForm'

  export default {
    name: 'ResumePage',
    components: {
      CreateUserForm,
      CreateResumeForm
    },
    data () {
      return {
        activeName: 'vacancies',
        vacancies: [
          {
            date: '2016-05-03',
            title: 'Tom',
            address: 'No. 189, Grove St, Los Angeles'
          },
          {
            date: '2016-05-02',
            title: 'John',
            address: 'No. 189, Grove St, Los Angeles'
          },
          {
            date: '2016-05-04',
            title: 'Morgan',
            address: 'No. 189, Grove St, Los Angeles'
          },
          {
            date: '2016-05-01',
            title: 'Jessy',
            address: 'No. 189, Grove St, Los Angeles'
          }
        ],
        resumes: []
      }
    },
    methods: {
      handleEdit (index, row) {
        console.log(index, row)
      },
      handleClick () {
      },
      onCreateUserButtonClick () {
        this.$refs['createUserForm'].open()
      },
      onCreateResumeButtonClick () {
        this.$refs['createResumeForm'].open()
      }
    },
  }
</script>

<style scoped>
  .app-container {
    max-width: 750px;
    margin: 0 auto;
  }

  .content {
    margin-top: 45px;
  }
</style>
