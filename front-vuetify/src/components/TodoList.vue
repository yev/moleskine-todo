<template>
  <v-list lines="one" :items="items" >
    <v-list-item v-for="item in items" :key="item.id">
      {{ item.id }} - {{ item.title }}
    </v-list-item>
  </v-list>
</template>
<script>
import axios from 'axios';

export default {
  data() {
    return {
      headers: [
        {text: 'ID', value: 'id'},
        {text: 'Name', value: 'name'},
        {text: 'Email', value: 'email'},
// Add more fields as needed
      ],
      items: [],
      search: '',
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    async fetchData() {
      try {
        const response = await axios.get('http://localhost:3000/api/todos/search?title=yev');
        this.items = response.data; // Assuming response data is an array
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
  },
};
</script>
