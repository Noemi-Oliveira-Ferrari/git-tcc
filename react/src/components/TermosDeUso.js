import React, { Component } from 'react';
import {Link} from 'react-router';
import '../css/cadastro-pro.css';
import Botao from './Botao';


export class TermosDeUso extends Component{
    render(){
        return(
            <div className="flex-center">
                <div className="card-formulario-termos">
                    <h3 className="title-card">Termos de Uso</h3>
                    <div className="flex-center caixa-termos">
                        <div className="text-termos">
                            <p className="termo">
                                Lorem ipsum dolor sit amet consectetur adipisicing elit. Itaque sint obcaecati dignissimos dolorum iste consequuntur repellendus aliquid illum! Numquam odio ad consectetur. Quisquam culpa facilis laborum quo inventore! Dolore ullam provident nam nobis cumque veritatis laudantium nostrum repellat explicabo minus, sapiente magni sunt commodi debitis praesentium ut eius hic vero. Beatae at ducimus repudiandae sunt officia, pariatur odio quod reiciendis aut eos rem? Libero odio enim quam, placeat, magni, officia neque ab ipsum quas at facilis repellendus quibusdam dolore quasi! Impedit suscipit sint officia inventore molestias deleniti hic ad eaque id veniam est, optio laboriosam aperiam architecto laudantium beatae fugiat facilis libero reiciendis obcaecati odit porro voluptates. Quibusdam soluta omnis tenetur eum laborum maiores distinctio incidunt odio illum, ipsam velit maxime esse culpa dolorum nostrum nam exercitationem quia consequuntur nihil iusto sequi fugit iste ut magnam. Inventore corporis sit maxime ratione maiores. Ad eligendi veritatis excepturi nobis. Vero ipsa dolorum eius sapiente velit eveniet voluptatibus alias illum, excepturi quo rem accusantium! Ipsam reprehenderit, consectetur distinctio iste dolor exercitationem ducimus quo laudantium, nam officiis fugiat id natus deleniti et dolorem dolorum odit, tempora dolore nobis voluptatum suscipit facilis esse harum quidem? Explicabo incidunt necessitatibus laudantium illo! Voluptates officiis minima possimus explicabo ut vitae error, doloremque sequi? Maxime similique cupiditate nam neque fugiat aliquid excepturi, voluptas veritatis? Similique quis neque, velit, molestias, autem ipsa voluptate inventore molestiae nihil ad provident tempore hic. Dolores, voluptas sapiente soluta illum beatae vitae eius optio quibusdam distinctio dolor quia est alias tempore voluptatem amet itaque dolore mollitia. Repudiandae beatae, reprehenderit omnis velit aut deleniti placeat dolores! Impedit necessitatibus adipisci itaque debitis? Debitis dicta nobis vel, sunt, esse adipisci est non officiis, aliquid sit quasi itaque quod! Molestiae rerum magni facilis animi recusandae fugit expedita minus ab quisquam voluptatum laboriosam, odit consequuntur unde amet quibusdam tempora assumenda cum doloremque quia velit at in explicabo? Nesciunt voluptatem illum accusamus vero pariatur porro maiores consequuntur maxime harum, officiis doloremque, distinctio, voluptatibus non qui? Fuga necessitatibus dolorum, quasi quisquam totam aut. Fugiat, ab illo libero voluptatum eos, dicta reprehenderit similique expedita blanditiis dignissimos unde rem culpa ratione laborum ipsum sit neque alias animi quos! Beatae sapiente fugiat animi facilis commodi nemo voluptates tempore explicabo aspernatur inventore eveniet maiores consequuntur esse, neque, vel quisquam quae possimus odit asperiores dolor amet ipsa. Ipsa, asperiores voluptatibus. Velit id recusandae perspiciatis iste optio nulla vero obcaecati maxime, quasi, fugiat tempore qui consequatur incidunt earum consectetur voluptas dolorum laborum labore quas! Vel consectetur sed architecto quis dicta et reprehenderit eveniet ratione maiores ullam amet laboriosam corporis voluptas eos nisi nemo ad fugit officia numquam, beatae officiis, molestias illo id eaque! Eveniet necessitatibus excepturi asperiores perferendis a ut exercitationem iure non quibusdam adipisci nesciunt ipsa doloribus, quaerat est, minima provident distinctio suscipit eius, minus commodi optio veritatis dolorum velit. Soluta aliquam expedita nam nostrum, modi laudantium magni, quaerat 
                            </p>
                        </div>
                    </div>
                    <div className="caixa-confirmacao-termos">
                        <input required type="checkbox" id="chk-termos" className="confirmar-termos" name="chk_termos"/><label htmlFor="chk-termos">Declaro estar ciente com os <span className="negrito">Termos de Uso e Politica de privacidade</span></label>
                    </div>
                    <div className="caixa-voltar-termos">
                        <Link className="link" to="/escolha">
                            <Botao classBotao="btn-voltar flex-center" typeBotao="button" name="btn_voltar" id="btn-voltar" valueBotao="VOLTAR"/>
                        </Link>
                    </div>
                    <div className="caixa-continuar-termos">
                        <Botao classBotao="btn-prox flex-center" name="btn_prox" id="btn-prox" valueBotao="CONTINUAR" typeBotao="submit"/>
                    </div>
                </div>
            </div>
        );
    }

}

export default TermosDeUso;
