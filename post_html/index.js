
document.addEventListener('DOMContentLoaded', () => {
    const postForm = document.getElementById('postForm');
    const editPostForm = document.getElementById('editPostForm');
    const postTableBody = document.querySelector('table tbody');
    const addPostModal = new bootstrap.Modal(document.getElementById('addPostModal'));
    const editPostModal = new bootstrap.Modal(document.getElementById('editPostModal'));

    const fetchPosts = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/posts'); // Replace with your API endpoint
            const posts = response.data;
            postTableBody.innerHTML = '';
            posts.forEach(post => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${post.id}</td>
                    <td>${post.title}</td>
                    <td>${post.description}</td>
                    <td><img src="http://localhost:8080/api/file/${post.id}" alt="Image" style="width: 100px;"></td>
                    <td>
                        <button class="btn btn-sm btn-primary edit-post" data-id="${post.id}" data-title="${post.title}" data-description="${post.description}" data-image="${post.image}">Edit</button>
                        <button class="btn btn-sm btn-danger delete-post" data-id="${post.id}">Delete</button>
                    </td>
                `;
                postTableBody.appendChild(tr);
            });
        } catch (error) {
            console.error('Error fetching posts', error);
        }
    };

    postForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const formData = new FormData(postForm);
        console.log(formData)
        try {
            await axios('http://localhost:8080/api/posts', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                method: 'POST',
            });
            addPostModal.hide();
            postForm.reset();
            fetchPosts();
        } catch (error) {
            console.error('Error adding post', error);
        }
    });

    editPostForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const postId = document.getElementById('editPostId').value;
        const formData = new FormData(editPostForm);
        try {
            await axios(`/api/posts/${postId}`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                },
                method: 'PUT',
            });
            editPostModal.hide();
            editPostForm.reset();
            fetchPosts();
        } catch (error) {
            console.error('Error editing post', error);
        }
    });

    postTableBody.addEventListener('click', (e) => {
        if (e.target.classList.contains('edit-post')) {
            const postId = e.target.getAttribute('data-id');
            const postTitle = e.target.getAttribute('data-title');
            const postDescription = e.target.getAttribute('data-description');
            const postImage = e.target.getAttribute('data-image');

            document.getElementById('editPostId').value = postId;
            document.getElementById('editTitle').value = postTitle;
            document.getElementById('editDescription').value = postDescription;
            document.getElementById('currentImagePreview').src = postImage;

            editPostModal.show();
        } else if (e.target.classList.contains('delete-post')) {
            const postId = e.target.getAttribute('data-id');
            if (confirm('Are you sure you want to delete this post?')) {
                axios(`/api/posts/${postId}`, {
                    method: 'DELETE'
                })
                    .then(() => fetchPosts())
                    .catch(error => console.error('Error deleting post', error));
            }
        }
    });

    fetchPosts();
});
