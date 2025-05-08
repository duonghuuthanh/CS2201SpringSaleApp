import { useContext, useState } from 'react';
import { Alert, Button, Table } from 'react-bootstrap';
import cookie from 'react-cookies'
import { MyCartContext, MyDispatchContext, MyUserContext } from '../configs/Contexts';
import { Link } from 'react-router-dom';
import { authApis, endpoints } from '../configs/Apis';

const Cart = () => {
    const [cart, setCart] = useState(cookie.load('cart') || null);
    const user = useContext(MyUserContext);
    const [,cartDispatch] = useContext(MyCartContext);
    
    const addReceipt = async () => {
        let res = await authApis().post(endpoints['receipt'], Object.values(cart));
        if (res.status === 200) {
            setCart(null);
            cartDispatch({
                "type": "paid"
            })
        }
    }

    return (
        <>
            <h1 className="text-center text-success mt-1">GIỎ HÀNG</h1>

            {cart === null ? <Alert>KHÔNG có sản phẩm nào trong giỏ</Alert>:<>
                <Table striped bordered hover>
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên sản phẩm</th>
                            <th>Đơn giá</th>
                            <th>Số lượng</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        {Object.values(cart).map(c => <tr key={c.id}>
                            <td>{c.id}</td>
                            <td>{c.name}</td>
                            <td>{c.price}</td>
                            <td>{c.quantity}</td>
                            <td>
                                <Button variant='danger'>&times;</Button>
                            </td>
                        </tr>)}
                        
                        
                    </tbody>
                    </Table>

                {user === null ? <Alert variant='warning'>Vui lòng <Link to="/login?next=/cart">đăng nhập</Link> để thanh toán!</Alert>: <>
                    <Button onClick={addReceipt} className='btn btn-danger mt-1 mb-1'>Thanh toán</Button>
                
                </>}
                
            </>}
        </>
    );
}

export default Cart;