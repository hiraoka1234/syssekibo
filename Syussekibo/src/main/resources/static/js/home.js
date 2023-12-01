/**
 * 
 */
// ボタン要素と入力フォーム要素を取得
const showFormButton = document.getElementById('showFormButton');
const deleteButton = document.getElementById('deleteButton');
const editButton = document.getElementById('editButton');


const editForm = document.getElementById('editForm');

// ボタンがクリックされたときの処理
showFormButton.addEventListener('click', () => {
    // 他のフォームを非表示にし、入力フォームを表示
    inputForm.style.display = 'block';
    deleteForm.style.display = 'none';
    editForm.style.display = 'none';
});

editButton.addEventListener('click', () => {
    // 編集フォームを表示し、他のフォームを非表示にする
    inputForm.style.display = 'none';
    deleteForm.style.display = 'none';
    editForm.style.display = 'block';
});